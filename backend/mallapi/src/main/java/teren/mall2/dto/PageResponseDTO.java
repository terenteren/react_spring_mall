package teren.mall2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<E> {

    private List<E> dtoList;         // 실제 데이터 리스트 (예: TodoDTO 리스트)
    private List<Integer> pageNumList; // 화면에 보여줄 페이지 번호 리스트

    private PageRequestDTO pageRequestDTO; // 클라이언트가 요청한 페이지 정보 객체

    private boolean prev, next; // 이전 페이지(true: 있음), 다음 페이지(true: 있음)
    private int totalCount;      // 전체 데이터 개수
    private int prevPage, nextPage; // 이전 페이지 번호, 다음 페이지 번호
    private int totalPage;       // 전체 페이지 개수
    private int current;         // 현재 페이지 번호

    /**
     * 생성자 - 페이징 관련 정보 계산 및 설정
     *
     * @param dtoList       현재 페이지에 해당하는 데이터 리스트
     * @param pageRequestDTO 사용자가 요청한 페이지 및 사이즈 정보
     * @param total         전체 데이터 개수
     */
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long total) {
        this.dtoList = dtoList; // 현재 페이지의 데이터 리스트 저장
        this.pageRequestDTO = pageRequestDTO; // 요청된 페이지 정보 저장
        this.totalCount = (int) total; // 전체 데이터 개수 저장

        // 끝 페이지(end) 계산
        // 사용자가 요청한 페이지 번호를 기준으로 10개 단위로 페이지 묶음을 만듦
        int end = (int) (Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;

        // 시작 페이지(start) 계산
        int start = end - 9; // 현재 블록의 첫 번째 페이지 번호

        // 전체 페이지(last) 계산
        int last = (int) Math.ceil(totalCount / (double) pageRequestDTO.getSize());

        // 현재 블록의 끝 페이지(end) 조정 (마지막 페이지를 초과하지 않도록 설정)
        end = Math.min(end, last);

        // 이전(prev), 다음(next) 페이지 존재 여부 설정
        this.prev = start > 1; // 1페이지보다 크면 이전 페이지 존재
        this.next = totalCount > end * pageRequestDTO.getSize(); // 다음 페이지가 존재하는지 여부

        // 페이지 번호 리스트(pageNumList) 생성
        this.pageNumList = IntStream.rangeClosed(start, end) // start부터 end까지 숫자 리스트 생성
                .boxed() // int → Integer 변환
                .collect(Collectors.toList());

        // 이전(prevPage), 다음(nextPage) 페이지 번호 설정
        this.prevPage = prev ? start - 1 : 0; // 이전 페이지 번호 (이전 페이지가 있다면 start - 1, 없으면 0)
        this.nextPage = next ? end + 1 : 0; // 다음 페이지 번호 (다음 페이지가 있다면 end + 1, 없으면 0)
        this.current = pageRequestDTO.getPage();
    }
}

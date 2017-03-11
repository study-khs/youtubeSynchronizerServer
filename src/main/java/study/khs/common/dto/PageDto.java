package study.khs.common.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageDto<T> {

	private int page;
	private int size;
	private boolean hasNext;
	private List<T> content;
}

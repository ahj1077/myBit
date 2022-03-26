package com.an.mybit.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class PostDTO {

    private Integer id;

    @NotEmpty(message = "코인심볼은 필수항목 입니다.")
    private String coinSymbol;

    @NotEmpty(message = "제목은 필수항목 입니다.")
    @Length(max = 100, message = "제목은 100자 이내여야 합니다.")
    private String	title;

    @NotEmpty(message = "내용을 필수항목 입니다.")
    @Length(max = 1000, message = "내용은 1000자 이내여야 합니다.")
    private String	content;

    @NotEmpty(message = "작성자는 필수항목 입니다.")
    @Length(max=50, message = "작성자는 50자 이내여얗 합니다.")
    private String writer;

    private int recommendCount;

    private String writeDttm;
}

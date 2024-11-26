package io.ksmrva.visual.torch.domain.entity.model.code.block;

import jakarta.persistence.*;

@Entity
@Table(name = "block")
public class Block {

    private int LINE_NUMBER_FILE_START = -1;

    private int LINE_NUMBER_FILE_END = -2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "code_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Code code;

    public Block() {
    }

    public Block(long id, Code code) {
        this.id = id;
        this.code = code;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Code getCode() {
        return code;
    }
    public void setCode(Code code) {
        this.code = code;
    }
}

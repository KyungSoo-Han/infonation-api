package kr.infonation.domain.base;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "sys_slip_no")
public class SlipNo implements Serializable{

    @Id
    private String slipGbn;
    @Id
    private String slipDate;

    private int count;

    public SlipNo(String slipGbn, String slipDate, int count) {
        this.slipGbn = slipGbn;
        this.slipDate = slipDate;
        this.count = count;
    }

    protected SlipNo() {

    }

    public void updateCount() {
        this.count = this.count + 1;
    }
}


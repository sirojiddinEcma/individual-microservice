package ai.ecma.organization.entity;

import ai.ecma.organization.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * BY SIROJIDDIN on 23.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Certificate extends AbsEntity {
    @Column(nullable = false)
    private String appNumber;

    private Date fromTime;

    private Date expiredDate;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(unique = true)
    private String blankNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User director;

    @ElementCollection
    private List<UUID> productList;
}

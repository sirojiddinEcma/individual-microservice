package ai.ecma.appapplicationindividual.entity;

import ai.ecma.appapplicationindividual.entity.enums.ApplicationStatusEnum;
import ai.ecma.appapplicationindividual.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class Application extends AbsEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private User client;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(nullable = false)
    private Integer organizationId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    @Enumerated(EnumType.STRING)
    private ApplicationStatusEnum applicationStatusEnum = ApplicationStatusEnum.NEW;

    @Column(columnDefinition = "text")
    private String reasonRejected;
}

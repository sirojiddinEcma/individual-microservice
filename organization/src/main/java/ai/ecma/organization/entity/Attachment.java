package ai.ecma.organization.entity;

import ai.ecma.organization.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 * BY SIROJIDDIN on 23.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends AbsEntity {
    private String name;
    private String contentType;
    private long size;

    @OneToOne(mappedBy = "attachment",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private AttachmentContent attachmentContent;
}

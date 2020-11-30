package ai.ecma.appapplicationindividual.repository;

import ai.ecma.appapplicationindividual.entity.Application;
import ai.ecma.appapplicationindividual.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {

}

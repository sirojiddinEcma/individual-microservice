package ai.ecma.appapplicationindividual.service;

import ai.ecma.appapplicationindividual.component.MessageByLang;
import ai.ecma.appapplicationindividual.entity.Application;
import ai.ecma.appapplicationindividual.entity.Product;
import ai.ecma.appapplicationindividual.entity.User;
import ai.ecma.appapplicationindividual.payload.ApiResponse;
import ai.ecma.appapplicationindividual.payload.ApplicationDto;
import ai.ecma.appapplicationindividual.payload.ProductDto;
import ai.ecma.appapplicationindividual.repository.ApplicationRepository;
import ai.ecma.appapplicationindividual.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BY SIROJIDDIN on 30.11.2020
 */


@Service
public class ApplicationService {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");

    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    MessageByLang messageByLang;
    @Autowired
    AttachmentRepository attachmentRepository;


    @Transactional(rollbackFor = Exception.class)
    public ApiResponse sendApplication(ApplicationDto applicationDto, User client) {
        try {
            Application application = new Application();
            application.setClient(client);
            application.setNumber(generateNumberApp(client.getTin()));
            application.setOrganizationId(applicationDto.getOrganizationId());
            application.setProducts(makeProductList(applicationDto.getProductDtoList(), application));
            application.setAttachments(attachmentRepository.findAllById(applicationDto.getAttachmentIds()));
            applicationRepository.save(application);
            return new ApiResponse(
                    messageByLang.getMessageByKey("application.sent"),
                    true
            );
        } catch (Exception e) {
            return new ApiResponse(
                    messageByLang.getMessageByKey("application.sent"),
                    false);
        }
    }

    private String generateNumberApp(String tin) {
        int countCurrentYear = applicationRepository.countCurrentYear();
        String year = simpleDateFormat.format(new Date());
        return year + "/" + tin + "/" +
                (countCurrentYear < 10 ? ("000" + countCurrentYear) : countCurrentYear < 100 ? ("00" + countCurrentYear)
                        : countCurrentYear < 1000 ? ("0" + countCurrentYear) : countCurrentYear);
    }

    private List<Product> makeProductList(List<ProductDto> productDtoList, Application application) {
        return productDtoList.stream().map(productDto -> makeProduct(productDto, application)).collect(Collectors.toList());
    }

    private Product makeProduct(ProductDto productDto, Application application) {
        return new Product(
                productDto.getName(),
                productDto.getCode(),
                productDto.getMeasurementId(),
                productDto.getAmount(),
                application

        );
    }

    public ApiResponse getApplications(int page, int size, UserDetails user, Integer orgId) {
        Pageable pageable=PageRequest.of(page, size);
        Page<Application> applicationPage = applicationRepository.findAllByOrganizationId(orgId, pageable);
        return new ApiResponse("Javob keldi tif dan", true, applicationPage.getContent());
    }
}

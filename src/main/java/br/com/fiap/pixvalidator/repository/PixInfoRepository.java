package br.com.fiap.pixvalidator.repository;

import br.com.fiap.pixvalidator.domain.PixInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface PixInfoRepository extends CrudRepository<PixInfo, String> {
}

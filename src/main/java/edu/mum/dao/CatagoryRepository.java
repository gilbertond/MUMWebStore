package edu.mum.dao;

import edu.mum.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD


=======
import org.springframework.stereotype.Repository;

@Repository
>>>>>>> niroj
public interface CatagoryRepository extends JpaRepository<ProductCategory,Long> {


}

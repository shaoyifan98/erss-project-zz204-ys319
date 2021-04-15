package edu.duke.erss.ups.dao;

import edu.duke.erss.ups.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDao {

    List<Product> getProductByShipID(@Param("shipID") long shipID);

    void insertNewProduct(@Param("product") Product product);
}

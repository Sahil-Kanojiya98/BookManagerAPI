package com.hello.BOOK_API.repository;

import com.hello.BOOK_API.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE " +
            "(:name IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:minPrice IS NULL OR b.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR b.price <= :maxPrice)")
    List<Book> searchByNameAndPriceRange(@Param("name") String name,
                                         @Param("minPrice") Integer minPrice,
                                         @Param("maxPrice") Integer maxPrice);

}

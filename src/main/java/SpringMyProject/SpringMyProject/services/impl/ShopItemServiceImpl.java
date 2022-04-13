package SpringMyProject.SpringMyProject.services.impl;

import SpringMyProject.SpringMyProject.entities.*;
import SpringMyProject.SpringMyProject.repositories.*;
import SpringMyProject.SpringMyProject.services.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopItemServiceImpl implements ShopItemService {


    @Autowired
    private CategoriesRepository categoryRepository;


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ShopItems addItem(ShopItems item) {
        return shopItemRepository.save(item);
    }

    @Override
    public ShopItems saveItem(ShopItems item) {
        return shopItemRepository.save(item);
    }

    @Override
    public Comments saveComment(Comments comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<ShopItems> getAllItems() {
        return shopItemRepository.findAll();
    }

    @Override
    public List<ShopItems> getAllItemsByCategory(Long id) {
        return shopItemRepository.findByCategories_Id(id);
    }

  /*  @Override
    public List<ShopItems> getAllItemsByCategory(String category) {
        return shopItemRepository.find;
    }*/

    @Override
    public ShopItems getItem(Long id) {
        Optional<ShopItems> opt = shopItemRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public List<Countries> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Countries getOneCountry(Long id) {
        Optional<Countries> opt = countryRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories getOneCategory(Long id) {
        Optional<Categories> opt = categoryRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Comments getOneCommentary(Long id) {
        Optional<Comments> opt = commentRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public void deleteComment(Comments comment) {
         commentRepository.delete(comment);
    }

    @Override
    public List<ShopItems> getAllItemsBySearch(String keyword) {
        return shopItemRepository.findByNameContainingIgnoreCase(keyword);
    }






   /* @Override
    public List<Comments> findCommentsByItem_id(Long id) {
        return commentRepository.findCommentsByItem_id(id);
    }*/


}

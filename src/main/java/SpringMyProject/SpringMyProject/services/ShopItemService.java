package SpringMyProject.SpringMyProject.services;

import SpringMyProject.SpringMyProject.entities.*;
import SpringMyProject.SpringMyProject.repositories.CommentRepository;

import java.util.List;

public interface ShopItemService {



    ShopItems addItem(ShopItems item);
    ShopItems saveItem(ShopItems item);
    Comments saveComment(Comments comment);
    List<ShopItems> getAllItems();
    List<ShopItems> getAllItemsByCategory(Long id);
    ShopItems getItem(Long id);
    List<Countries> getAllCountries();
    Countries getOneCountry(Long id);
    List<Categories> getAllCategories();
    Categories getOneCategory(Long id);
    Comments getOneCommentary(Long id);
    void deleteComment(Comments comment);
    List<ShopItems> getAllItemsBySearch(String keyword);
}

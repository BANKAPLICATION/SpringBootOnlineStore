package SpringMyProject.SpringMyProject.controllers;


import SpringMyProject.SpringMyProject.entities.*;
import SpringMyProject.SpringMyProject.repositories.OrderRepository;
import SpringMyProject.SpringMyProject.services.ShopItemService;
import SpringMyProject.SpringMyProject.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {


    @Value("${target.url}")
    private String targetURL;
    @Value("${loadURL}")
    private String loadURL;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    private ShopItemService shopItemService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(value = "main")
    public String main(Model model){
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        return "main";
    }





/*
    @PostMapping(value = "/payment")
    public String payment(@RequestParam(name = "amount") int amount,
                          @RequestParam(name = "cardnumber") String cardnumber,
                          @RequestParam(name = "expiration") String expiration,
                          @RequestParam(name = "cvv") String cvv,
                          @RequestParam(name = "item_id") Long item_id, Model model) {
        Users user = getUser();
                Orders order = new Orders();
                order.setAmount(amount);
                ShopItems item  = shopItemService.getItem(item_id);
                order.setCardnumber(cardnumber);
                order.setCvv(cvv);
                order.setExpiration(expiration);
                if(user != null) {
                    order.setUser_id(user.getId());
                }
                order.setItem_id(item_id);
                orderRepository.save(order);
                Set<ShopItems> items = new HashSet<>();
                items.add(item);
                if(user != null) {
                    user.setItems(items);
                    userService.saveUser(user);
                    model.addAttribute("success", "success");
                }
                return "redirect:/details/" +item_id;
    }*/

    @GetMapping(value = "/search")
    public String search(@RequestParam(name = "keyword") String keyword, Model model) {
        List<Categories> categories = shopItemService.getAllCategories();
            List<ShopItems> items = shopItemService.getAllItemsBySearch(keyword);
            model.addAttribute("items", items);
            model.addAttribute("categories", categories);
            return "search";
    }
    @GetMapping(value = "/")
    public String index(Model model){
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("items", getAllItems());
        return "index";
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/signin")
    public String login(Model model) {
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("currentUser", getUser());
        return "login";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        return "register";
    }

    @PostMapping(value = "/changePassword")
    public String changePassword(@RequestParam(name = "old_password") String old_password,
                                 @RequestParam(name = "new_password") String new_password,
                                 @RequestParam(name = "re_new_password") String re_new_password, Model model) {
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        if (re_new_password.equals(new_password)) {
            Users currentUser = getUser();
            System.out.println(currentUser.getPassword() + " asdad asd");
            if (passwordEncoder.matches(old_password, currentUser.getPassword() )) {
                currentUser.setPassword(passwordEncoder.encode(new_password));
                userService.addUser(currentUser);
                return "redirect:/profile?success";
            }
        }
        return "redirect:/profile?error";
    }
    @GetMapping(value = "/accessdenied")
    public String accessdenied(Model model) {
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("currentUser", getUser());
        return "accessdenied";
    }
    @PostMapping(value = "/tologin")
    public String tologin(@RequestParam(name = "email") String email,
                          @RequestParam(name = "password") String password,
                          Model model) {
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
       Users user = userService.findByEmail(email);
        if(user != null) {
            if (user.getPassword().equals(password)) {
                return "redirect:/login?success";
            }
        }
        return "redirect:/login?error";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profile(Model model){
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("currentUser", getUser());
        return "profile";
    }

    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name = "id") Long id, Model model) {
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("currentUser", getUser());
        model.addAttribute("categories", categories);
        ShopItems item = shopItemService.getItem(id);
        model.addAttribute("item", item);
        return "details";
    }
/*    @GetMapping(value = "/myorders/{id}")
    public String myorders(@PathVariable(name = "id") Long id, Model model) {
        List<Categories> categories = shopItemService.getAllCategories();
        Users user = userService.getUser(id);
        model.addAttribute("currentUser", getUser());
        model.addAttribute("categories", categories);
        if (user != null) {
            List<ShopItems> items = shopItemService.getAllItemsByOrder(user.getId());
            model.addAttribute("orders", orders);
        }
        return "/myorders";
    }*/


    @GetMapping(value = "/byCategory/{category}")
    public String byCategory(@PathVariable(name = "category") Long categoryId, Model model) {
        List<ShopItems> items = shopItemService.getAllItemsByCategory(categoryId);
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("items", items);
        return "byCategory";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin")
    public String admin(Model model){
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("currentUser", getUser());
        return "admin";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @GetMapping(value = "/moderator")
    public String moderator(Model model){
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        List<ShopItems> items = shopItemService.getAllItems();
        List<Countries> countries = shopItemService.getAllCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("tovary", items);
        model.addAttribute("currentUser", getUser());
        return "moderator";
    }


    @PostMapping(value = "/toregister")
    public String toregister(@RequestParam(name = "email") String email,
                          @RequestParam(name = "password") String password,
                             @RequestParam(name = "repassword") String repassowrd,
                             @RequestParam(name = "fullname") String fullname, Model model) {
        Users user = userService.findByEmail(email);
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        if(user == null) {
            if (repassowrd.equals(password)) {
                Set<Roles> roles =  new HashSet<>();
                Roles defaultUserRole = userService.getRoleByRoleName("ROLE_USER");
                roles.add(defaultUserRole);
                Users newUser = new Users();
                newUser.setEmail(email);
                newUser.setPassword(passwordEncoder.encode(password));
                newUser.setFullName(fullname);
                newUser.setRoles(roles);
                userService.addUser(newUser);
                return "redirect:/register?success";
            }
        }
        return "redirect:/register?error";
    }

    @PreAuthorize("isAuthenticated")
    @PostMapping(value = "/uploadavatar")
    public String uploadAvatar(@RequestParam(name = "avapic") MultipartFile file, Model model) {
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        if(file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg")) {
            try {
                Users user = getUser();
                String fileName = DigestUtils.sha1Hex("Syrym" + user.getId());
                byte[] bytes = file.getBytes();
                Path path = Paths.get(targetURL + fileName + ".jpg");
                Files.write(path, bytes);
                user.setAvatar(fileName);
                userService.saveUser(user);
                return "redirect:/profile?success";
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/profile";
    }








    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping(value = "/additem")
    public String addItem(@RequestParam(name = "avapic") MultipartFile file,
                          @RequestParam(name = "name") String name,
                          @RequestParam(name = "amount") int amount,
                          @RequestParam(name = "price") int price,
                          @RequestParam(name = "country_id") Long country_id,
                          @RequestParam(name = "category_id") Long category_id,
                          Model model) {
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        if(file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg")) {
            try {
                String nameImg = file.getOriginalFilename();
                String fileName = DigestUtils.sha1Hex("Syrym" + nameImg); /*user.getId()*/
                byte[] bytes = file.getBytes();
                Path path = Paths.get(targetURL + fileName + ".jpg");
                Files.write(path, bytes);
                ShopItems item = new ShopItems();
                item.setName(name);
                item.setAmount(amount);
                item.setPrice(price);

                Countries country = shopItemService.getOneCountry(country_id);
                if(country != null) {
                    item.setCountry(country);
                    item.setImages(fileName);
                    shopItemService.saveItem(item);
                    List<Categories> itemCategories = null;
                    if (itemCategories == null) {
                        itemCategories = new ArrayList<>();
                    }
                    Categories category = shopItemService.getOneCategory(category_id);
                    itemCategories.add(category);
                    item.setCategories(itemCategories);
                    shopItemService.saveItem(item);
                    return "redirect:/moderator?success";
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/moderator";
    }





    @GetMapping(value = "/viewava/{avatarURL}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] viewAva(@PathVariable(name = "avatarURL") String avatarURL, Model model) throws IOException {
        List<Categories> categories = shopItemService.getAllCategories();
        model.addAttribute("categories", categories);
        String pictureURL = loadURL + "user_default.png";
        if (avatarURL!=null) {
            pictureURL = loadURL + avatarURL + ".jpg";
        }


        InputStream in;
        try {

            ClassPathResource resource = new ClassPathResource(pictureURL);
            in = resource.getInputStream();

        }
        catch (Exception e) {

            ClassPathResource resource = new ClassPathResource(loadURL + "user_default.png");
            in = resource.getInputStream();

        }
        return IOUtils.toByteArray(in);
    }

    private List<ShopItems> getAllItems() {
       return shopItemService.getAllItems();
    }
    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users user = (Users)authentication.getPrincipal();
            return user;
        }else {
            return null;
        }
    }



}

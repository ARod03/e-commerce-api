🎮 Video Game Shop API
A full‑featured Spring Boot REST API powering a digital Video Game Shop, where users can browse games, manage their shopping cart, and prepare for checkout.
Built as part of the YearUp Java Full‑Stack Development Capstone.

📝 Project Description
The Video Game Shop is an e‑commerce backend designed to simulate a real online store that sells video games.
It provides:

A browsable product catalog

Category filtering

Secure user authentication

A persistent shopping cart

Admin‑only product management

The system is built using Spring Boot, MySQL, and JWT‑based authentication, following clean service‑layer architecture and RESTful design principles.

How the Video Game Shop Works
Users can:

Register and log in

Browse all available video games

Filter by category (e.g., Action, RPG, Sports)

View product details

Add games to their shopping cart

Update quantities or remove items

Clear their cart entirely

Admins can:

Add new games

Edit existing games

Fix inventory levels

Manage categories

The API is designed to integrate seamlessly with a frontend store interface, allowing customers to shop, manage their cart, and eventually proceed to checkout.

🚀 Main Features
🎮 Product Catalog
Browse all video games

Filter by category, price range, or featured status

View detailed product information

Admin‑only CRUD operations

Inventory (stock) tracking — including the fixed stock update bug

🗂️ Category Management
Retrieve all categories

Admin‑only category creation and updates

🔐 User Authentication
Secure login with JWT

Role‑based access (ROLE_USER, ROLE_ADMIN)

Protected endpoints using @PreAuthorize

🛒 Shopping Cart
The shopping cart is stored in the database and rebuilt dynamically for each user.

Users can:

Add games to their cart

Increase or decrease quantities

Remove individual items

Clear the entire cart

View a calculated total

The cart returned to the frontend is an in‑memory ShoppingCart model built from database rows, including product details and line totals.

🛠️ Technologies Used
Layer	Technology
Backend	Java 17, Spring Boot
Security	Spring Security, JWT
Database	MySQL, JPA/Hibernate
Testing	JUnit 5, Mockito
Tools	IntelliJ IDEA, Insomnia


📁 Project Structure
Code
src/main/java/org/yearup/
│
├── controllers/
│   ├── ProductsController
│   ├── CategoriesController
│   ├── ShoppingCartController
│   └── AuthenticationController
│
├── models/
│   ├── Product
│   ├── Category
│   ├── CartItem        (DB entity)
│   ├── ShoppingCart    (in‑memory cart)
│   └── ShoppingCartItem
│
├── repository/
│   ├── ProductRepository
│   ├── CategoryRepository
│   └── ShoppingCartRepository
│
├── service/
│   ├── ProductService
│   ├── ShoppingCartService
│   └── UserService
│
└── security/
    ├── JwtRequestFilter
    ├── JwtTokenUtil
    └── SecurityConfig

    
🧪 Testing With Insomnia
The project includes a full suite of API tests covering:

Authentication

Product browsing

Admin product management

Shopping cart operations

Error handling (401, 403, 404, etc.)

To run:

Start the Spring Boot server

Import the Insomnia JSON file

Run tests in order (1.x → 2.x → 3.x)

🐞 Notable Fixes
✔ Stock Update Bug
A missing setter in ProductService.update() prevented stock from updating.
This was corrected and verified with a JUnit test.

✔ Search Bug
Search results incorrectly filtered by featured status.
Logic was corrected to return all matching products.

▶️ Running the Application
1. Configure MySQL
sql
CREATE DATABASE videogamestore;
Update application.properties:

Code
spring.datasource.url=jdbc:mysql://localhost:3306/videogamestore
spring.datasource.username=yourUser
spring.datasource.password=yourPassword
2. Run the server
In IntelliJ:

Code
ECommerceApplication → Run
3. Test with Insomnia
Use the provided API test suite.

🧩 Interesting Code Snippet
One of the most interesting parts of this project is how the system adds a product to the shopping cart.
This method demonstrates:

Database lookup

Conditional logic

Entity creation

Quantity updates

Returning a fully rebuilt cart

Code Snippet: Adding a Product to the Cart

```
java
public ShoppingCart addProductToCart(int userId, int productId){
    CartItem cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId);

    if(cartItem == null){
        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setUserId(userId);
        shoppingCartRepository.save(item);
    } else {
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        shoppingCartRepository.save(cartItem);
    }

    return getByUserId(userId);
}
```
Why This Code Is Interesting

It handles both scenarios:

The product is not yet in the cart → create a new row

The product already exists → increment quantity

It uses the repository to save changes immediately.

It returns a fully rebuilt ShoppingCart, which includes:

Product details

Quantities

Line totals

Cart total

This method is a great example of how the service layer coordinates between the database and the in‑memory cart model to produce a clean, frontend‑ready response.

🔮 Future Enhancements
The next planned features include:

👤 User Profile
Saved addresses

Saved payment methods

Order history

Profile editing

💳 Checkout System
Convert cart to order

Deduct inventory

Save order details

Return order confirmation

These enhancements will complete the full e‑commerce experience.

👤 Author
Adrian Rodriguez  
Java Application Developer Student
YearUp Capstone Project 3

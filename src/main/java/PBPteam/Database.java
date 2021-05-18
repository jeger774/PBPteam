package PBPteam;
import PBPteam.model.Part;
import PBPteam.model.Product;
import PBPteam.model.Rent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class Database {
    public static void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Driver:" + meta.getDriverName());
                System.out.println("A database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewProductTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:pbp.db";

        // SQL statement for creating a new table
        @SuppressWarnings("all")
        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	price real,\n"
                + "	stock int\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    public void insertProduct(String name, double price, int stock) {
        String sql = "INSERT INTO products(name,price,stock) VALUES(?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, stock);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:pbp.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @SuppressWarnings("Duplicates")
    public ObservableList<Product> selectAllProducts(){
        String sql = "SELECT id, name, price, stock FROM products";
        ObservableList<Product> products = FXCollections.observableArrayList();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setStock(rs.getInt("stock"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    @SuppressWarnings("Duplicates")
    public ObservableList<Product> selectAllProductsByName(String name){
        String sql = "SELECT id, name, price, stock FROM products WHERE name = ?";
        ObservableList<Product> products = FXCollections.observableArrayList();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))

             {
            // loop through the result set
            pstmt.setString(1, name);
                 ResultSet rs    = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setStock(rs.getInt("stock"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public Product selectProduct(String name) {
        String sql = "SELECT id, name, price, stock FROM products WHERE name = ?";

        Product product = new Product();
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, name);

            // execute the delete statement
            ResultSet rs    = pstmt.executeQuery();
            rs.next();
            product.setProductId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setStock(rs.getInt("stock"));
            product.setPrice(rs.getDouble("price"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    public void updateProduct(int id, String name, double price, int stock) {
        String sql = "UPDATE products SET name = ? , "
                + "price = ? ,"
                + "stock = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, stock);
            pstmt.setInt(4, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProductWhenRented(String name, int stock) {
        String sql = "UPDATE products SET stock = ?  "
                + "WHERE name = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, stock);
            pstmt.setString(2, name);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //part
    public static void createNewPartTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:pbp.db";

        // SQL statement for creating a new table
        @SuppressWarnings("all")
        String sql = "CREATE TABLE IF NOT EXISTS parts (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	price real,\n"
                + "	stock int\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    public void insertPart(String name, double price, int stock) {
        String sql = "INSERT INTO parts(name,price,stock) VALUES(?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, stock);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePart(int id) {
        String sql = "DELETE FROM parts WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    public ObservableList<Part> selectAllPart(){
        String sql = "SELECT id, name, price, stock FROM parts";
        ObservableList<Part> parts = FXCollections.observableArrayList();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                Part part = new Part();
                part.setPartId(rs.getInt("id"));
                part.setName( rs.getString("name"));
                part.setStock(rs.getInt("stock"));
                part.setPrice(rs.getDouble("price"));
                parts.add(part);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return parts;
    }

    @SuppressWarnings("Duplicates")
    public ObservableList<Part> selectAllPartsByName(String name){
        String sql = "SELECT id, name, price, stock FROM parts WHERE name = ?";
        ObservableList<Part> parts = FXCollections.observableArrayList();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))

        {
            // loop through the result set
            pstmt.setString(1, name);
            ResultSet rs    = pstmt.executeQuery();
            while (rs.next()) {
                Part part = new Part();
                part.setPartId(rs.getInt("id"));
                part.setName(rs.getString("name"));
                part.setStock(rs.getInt("stock"));
                part.setPrice(rs.getDouble("price"));
                parts.add(part);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return parts;
    }

    @SuppressWarnings("Duplicates")
    public void updatePart(int id, String name, double price, int stock) {
        String sql = "UPDATE parts SET name = ? , "
                + "price = ? ,"
                + "stock = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, stock);
            pstmt.setInt(4, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //rent
    public static void createNewRentTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:pbp.db";

        // SQL statement for creating a new table
        @SuppressWarnings("all")
        String sql = "CREATE TABLE IF NOT EXISTS rents (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	stock int,\n"
                + "	timeleft text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertRent(String name, int stock, String timeleft) {
        String sql = "INSERT INTO rents(name,stock,timeleft) VALUES(?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, stock);
            pstmt.setString(3, timeleft);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    public ObservableList<Rent> selectAllRents() {
        String sql = "SELECT id, name, stock, timeleft FROM rents";
        ObservableList<Rent> rents = FXCollections.observableArrayList();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                Rent rent = new Rent();
                rent.setProductId(rs.getInt("id"));
                rent.setName(rs.getString("name"));
                rent.setStock(rs.getInt("stock"));
                rent.setTimeLeft(rs.getString("timeleft"));
                rents.add(rent);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rents;
    }

    @SuppressWarnings("Duplicates")
    public ObservableList<Rent> selectAllRentsByName(String name){
        String sql = "SELECT id, name, stock, timeleft FROM rents WHERE name = ?";
        ObservableList<Rent> rents = FXCollections.observableArrayList();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // loop through the result set
            pstmt.setString(1, name);
            ResultSet rs    = pstmt.executeQuery();
            while (rs.next()) {
                Rent rent = new Rent();
                rent.setProductId(rs.getInt("id"));
                rent.setName(rs.getString("name"));
                rent.setStock(rs.getInt("stock"));
                rent.setTimeLeft(rs.getString("timeleft"));
                rents.add(rent);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rents;
    }

    public void deleteRent(String name) {
        String sql = "DELETE FROM rents WHERE name = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, name);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRent(int id, String timeleft) {
        String sql = "UPDATE rents SET timeleft = ?  "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, timeleft);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewDatabase("pbp.db");
        createNewProductTable();
        createNewPartTable();
        createNewRentTable();
        @SuppressWarnings("unused")
        Database database = new Database();
    }
}
package org.example.Gui;

import org.example.DTO.ClientDTO;
import org.example.DTO.LogDTO;
import org.example.DTO.OrdersDTO;
import org.example.DTO.ProductDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.List;

/**
 * This class provides the View functionality for the GUI.
 */
public class View  extends JFrame {

    /**
     * Constructs a new View.
     *
     * @param title the title of the view
     */
    public View(String title) {
        JPanel mainPanel = new JPanel();
        this.setTitle(title);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        prepareTopBar();

        JLabel welcomeLabel = new JLabel("Welcome to the order management system");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(welcomeLabel);

        this.setContentPane(mainPanel);
    }


    /**
     * Sets the main panel of the view.
     *
     * @param panel the panel to set as the main panel
     */
    public void setMainPanel(JPanel panel){
        this.setContentPane(panel);
    }


    private JPanel editProductPane(){
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel productPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name");
        JComboBox<String> nameField = new JComboBox<>();
        Controller.getAllProductsName().forEach(nameField::addItem);

        JLabel stockLabel = new JLabel("Stock");
        JTextField stockField = new JTextField();

        JLabel priceLabel = new JLabel("Email");
        JTextField priceField = new JTextField();

        productPanel.add(nameLabel);
        productPanel.add(nameField);
        productPanel.add(stockLabel);
        productPanel.add(stockField);
        productPanel.add(priceLabel);
        productPanel.add(priceField);

        JLabel editProductLabel = new JLabel("Edit product");
        editProductLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(editProductLabel);
        pane.add(productPanel);

        nameField.addActionListener(e -> {
            if(nameField.getSelectedItem() == null){
                return;
            }
            ProductDTO productDTO = Controller.getAllProducts().stream()
                    .filter(product -> product.getName().equals(Objects.requireNonNull(nameField.getSelectedItem()).toString()))
                    .findFirst()
                    .orElse(null);
            stockField.setText("" + productDTO.getStock());
            priceField.setText("" + productDTO.getPrice());
        });
        if (nameField.getItemCount() > 0) {
            nameField.setSelectedIndex(0);
        }

        Box buttonBox = Box.createHorizontalBox();
        JButton editproductButton = new JButton("Edit Product");
        editproductButton.addActionListener(e -> {
            if(nameField.getSelectedItem() == null){
                JOptionPane.showMessageDialog(this, "Please select a Product");
                return;
            }
            if(stockField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }
            ProductDTO productDTO = new ProductDTO(Objects.requireNonNull(nameField.getSelectedItem()).toString(), Double.parseDouble(priceField.getText().trim()), Integer.parseInt(stockField.getText().trim()));
            Controller.editProduct(Objects.requireNonNull(nameField.getSelectedItem()).toString(), productDTO);
            nameField.setSelectedIndex(nameField.getSelectedIndex());
        });
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(editproductButton);
        buttonBox.add(Box.createHorizontalGlue());

        pane.add(buttonBox);

        return pane;
    }
    private JPanel editClientPane(){
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel clientPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        clientPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name");
        JComboBox<String> nameField = new JComboBox<>();
        Controller.getAllClientsNames().forEach(nameField::addItem);

        JLabel addressLabel = new JLabel("Address");
        JTextField addressField = new JTextField();

        JLabel emailLabel = new JLabel("Email");
        JTextField emailField = new JTextField();

        clientPanel.add(nameLabel);
        clientPanel.add(nameField);
        clientPanel.add(addressLabel);
        clientPanel.add(addressField);
        clientPanel.add(emailLabel);
        clientPanel.add(emailField);

        JLabel editClientLabel = new JLabel("Edit Client");
        editClientLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(editClientLabel);
        pane.add(clientPanel);

        nameField.addActionListener(e -> {
            if(nameField.getSelectedItem() == null){
                return;
            }
            ClientDTO clientDTO = Controller.getAllClients().stream()
                    .filter(client -> client.getName().equals(Objects.requireNonNull(nameField.getSelectedItem()).toString()))
                    .findFirst()
                    .orElse(null);
            addressField.setText(clientDTO.getAddress());
            emailField.setText(clientDTO.getEmail());
        });

        if (nameField.getItemCount() > 0) {
            nameField.setSelectedIndex(0);
        }

        Box buttonBox = Box.createHorizontalBox();
        JButton editClientButton = new JButton("Edit Client");
        editClientButton.addActionListener(e -> {
            if(nameField.getSelectedItem() == null){
                JOptionPane.showMessageDialog(this, "Please select a client");
                return;
            }
            if(addressField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }
            ClientDTO clientDTO = new ClientDTO(Objects.requireNonNull(nameField.getSelectedItem()).toString(), addressField.getText().trim(), emailField.getText().trim());
            Controller.editClient(Objects.requireNonNull(nameField.getSelectedItem()).toString(), clientDTO);
            nameField.setSelectedIndex(nameField.getSelectedIndex());
        });
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(editClientButton);
        buttonBox.add(Box.createHorizontalGlue());

        pane.add(buttonBox);

        return pane;
    }

    private JPanel deleteClientPane() {
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel clientPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        clientPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name");
        JComboBox<String> nameField = new JComboBox<>();
        Controller.getAllClientsNames().forEach(nameField::addItem);

        clientPanel.add(nameLabel);
        clientPanel.add(nameField);

        JLabel deleteClientLabel = new JLabel("Delete Client");
        deleteClientLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(deleteClientLabel);
        pane.add(clientPanel);

        Box buttonBox = Box.createHorizontalBox();
        JButton deleteClientButton = new JButton("Delete Client");
        deleteClientButton.addActionListener(e -> {
            if(nameField.getSelectedItem() == null){
                JOptionPane.showMessageDialog(this, "Please select a client");
                return;
            }
            Controller.deleteClient(Objects.requireNonNull(nameField.getSelectedItem()).toString());
            nameField.removeAllItems();
            Controller.getAllClientsNames().forEach(nameField::addItem);
            repaint();
            revalidate();
        });
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(deleteClientButton);
        buttonBox.add(Box.createHorizontalGlue());

        pane.add(buttonBox);

        return pane;
    }

    private JPanel deleteProductPane(){
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel productPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name");
        JComboBox<String> nameField = new JComboBox<>();
        Controller.getAllProductsName().forEach(nameField::addItem);

        productPanel.add(nameLabel);
        productPanel.add(nameField);

        JLabel deleteProductLabel = new JLabel("Delete Product");
        deleteProductLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(deleteProductLabel);
        pane.add(productPanel);

        Box buttonBox = Box.createHorizontalBox();
        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(e -> {
            if(nameField.getSelectedItem() == null){
                JOptionPane.showMessageDialog(this, "Please select a product");
                return;
            }
            Controller.deleteProduct(Objects.requireNonNull(nameField.getSelectedItem()).toString());
            nameField.removeAllItems();
            Controller.getAllProductsName().forEach(nameField::addItem);
            repaint();
            revalidate();
        });
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(deleteProductButton);
        buttonBox.add(Box.createHorizontalGlue());

        pane.add(buttonBox);

        return pane;
    }

    private JPanel createOrderPane(){
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel orderPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        orderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel clientLabel = new JLabel("Client");
        JComboBox<String> clientField = new JComboBox<>();
        Controller.getAllClientsNames().forEach(clientField::addItem);

        JLabel productLabel = new JLabel("Product");
        JComboBox<String> productField = new JComboBox<>();
        Controller.getAllProductsName().forEach(productField::addItem);

        JLabel quantityLabel = new JLabel("Quantity");
        JTextField quantityField = new JTextField();

        orderPanel.add(clientLabel);
        orderPanel.add(clientField);
        orderPanel.add(productLabel);
        orderPanel.add(productField);
        orderPanel.add(quantityLabel);
        orderPanel.add(quantityField);

        JLabel addProductLabel = new JLabel("Add Order");
        addProductLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(addProductLabel);
        pane.add(orderPanel);

        Box buttonBox = Box.createHorizontalBox();
        JButton addOrderButton = new JButton("Add Order");
        addOrderButton.addActionListener(e -> {
            if(quantityField.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }
            try{
                OrdersDTO ordersDTO = new OrdersDTO(Objects.requireNonNull(clientField.getSelectedItem()).toString(),
                        Objects.requireNonNull(productField.getSelectedItem()).toString(),
                        Integer.parseInt(quantityField.getText().trim()));
                Controller.createOrder(ordersDTO);
            } catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Something went wrong");
            }
            finally {
                clientField.setSelectedIndex(0);
                productField.setSelectedIndex(0);
                quantityField.setText("");
            }

        });
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(addOrderButton);
        buttonBox.add(Box.createHorizontalGlue());

        pane.add(buttonBox);

        return pane;
    }

    private JPanel createClientPane(){
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel clientPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        clientPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name");
        JTextField nameField = new JTextField();

        JLabel addressLabel = new JLabel("Address");
        JTextField addressField = new JTextField();

        JLabel emailLabel = new JLabel("Email");
        JTextField emailField = new JTextField();

        clientPanel.add(nameLabel);
        clientPanel.add(nameField);
        clientPanel.add(addressLabel);
        clientPanel.add(addressField);
        clientPanel.add(emailLabel);
        clientPanel.add(emailField);

        JLabel addClientLabel = new JLabel("Add Client");
        addClientLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(addClientLabel);
        pane.add(clientPanel);

        Box buttonBox = Box.createHorizontalBox();
        JButton addClientButton = new JButton("Add Client");
        addClientButton.addActionListener(e -> {
            if(nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty() || addressField.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }
            ClientDTO clientDTO = new ClientDTO(nameField.getText().trim(), addressField.getText().trim(), emailField.getText().trim());
            Controller.createClient(clientDTO);
            addressField.setText("");
            emailField.setText("");
            nameField.setText("");
        });
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(addClientButton);
        buttonBox.add(Box.createHorizontalGlue());

        pane.add(buttonBox);

        return pane;
    }

    private JPanel createProductPane(){
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel productPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name");
        JTextField nameField = new JTextField();

        JLabel priceLabel = new JLabel("Price");
        JTextField priceField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity");
        JTextField quantityField = new JTextField();

        productPanel.add(nameLabel);
        productPanel.add(nameField);
        productPanel.add(priceLabel);
        productPanel.add(priceField);
        productPanel.add(quantityLabel);
        productPanel.add(quantityField);

        JLabel addProductLabel = new JLabel("Add Product");
        addProductLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(addProductLabel);
        pane.add(productPanel);

        Box buttonBox = Box.createHorizontalBox();
        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(e -> {
            if(nameField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty() || quantityField.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }
            ProductDTO productDTO = new ProductDTO(nameField.getText().trim(),  Double.parseDouble(priceField.getText().trim()), Integer.parseInt(quantityField.getText().trim()));
            Controller.createProduct(productDTO);
            nameField.setText("");
            priceField.setText("");
            quantityField.setText("");
        });
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(addProductButton);
        buttonBox.add(Box.createHorizontalGlue());

        pane.add(buttonBox);

        return pane;
    }

    /**
     * Prepares the top bar of the view.
     */
    private void prepareTopBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu clientMenu = new JMenu("Client");
        JMenu orderMenu = new JMenu("Order");
        JMenu productMenu = new JMenu("Product");
        JMenu logMenu = new JMenu("Logs");

        JMenuItem viewLogs = new JMenuItem("View Logs");
        viewLogs.addActionListener(e -> {
            List<LogDTO> logs = Controller.getAllLogs();
            populateTable(logs);
            revalidate();
            repaint();
        });


        JMenuItem addClient = new JMenuItem("Add Client");
        addClient.addActionListener(e -> {
            setMainPanel(createClientPane());
            revalidate();
            repaint();
        });
        JMenuItem viewClients = new JMenuItem("View Clients");
        viewClients.addActionListener(e -> {
            List<ClientDTO> clients = Controller.getAllClients();
            populateTable(clients);
            revalidate();
            repaint();
        });
        JMenuItem editClient = new JMenuItem("Edit Client");
        editClient.addActionListener(e -> {
            setMainPanel(editClientPane());
            revalidate();
            repaint();
        });
        JMenuItem deleteClient = new JMenuItem("Delete Client");
        deleteClient.addActionListener(e ->{
            setMainPanel(deleteClientPane());
            revalidate();
            repaint();
        });

        JMenuItem addOrder = new JMenuItem("Add Order");
        addOrder.addActionListener(e -> {
            setMainPanel(createOrderPane());
            revalidate();
            repaint();
        });
        JMenuItem viewOrders = new JMenuItem("View Orders");
        viewOrders.addActionListener(e -> {
            List<OrdersDTO> orders = Controller.getAllOrders();
            populateTable(orders);
            revalidate();
            repaint();
        });

        JMenuItem addProduct = new JMenuItem("Add Product");
        addProduct.addActionListener(e -> {
            setMainPanel(createProductPane());
            revalidate();
            repaint();
        });
        JMenuItem viewProducts = new JMenuItem("View Products");
        viewProducts.addActionListener(e -> {
            List<ProductDTO> products = Controller.getAllProducts();
            populateTable(products);
            revalidate();
            repaint();
        });
        JMenuItem editProduct = new JMenuItem("Edit Product");
        editProduct.addActionListener(e -> {
            setMainPanel(editProductPane());
            revalidate();
            repaint();
        });
        JMenuItem deleteProduct = new JMenuItem("Delete Product");
        deleteProduct.addActionListener(e -> {
            setMainPanel(deleteProductPane());
            revalidate();
            repaint();
        });

        clientMenu.add(addClient);
        clientMenu.add(viewClients);
        clientMenu.add(editClient);
        clientMenu.add(deleteClient);

        orderMenu.add(addOrder);
        orderMenu.add(viewOrders);

        productMenu.add(addProduct);
        productMenu.add(viewProducts);
        productMenu.add(editProduct);
        productMenu.add(deleteProduct);

        logMenu.add(viewLogs);

        menuBar.add(clientMenu);
        menuBar.add(orderMenu);
        menuBar.add(productMenu);
        menuBar.add(logMenu);



        this.setJMenuBar(menuBar);
    }

    /**
     * Populates the table with the given objects.
     *
     * @param objects the objects to populate the table with
     */
    private void populateTable(List<?> objects){
        if(objects.isEmpty()){
            JOptionPane.showMessageDialog(this, "No data to display");
        }

        Object firstObject = objects.get(0);
        Class<?> objectClass = firstObject.getClass();
        Field[] fields = objectClass.getDeclaredFields();

        String[] columnNames = Arrays.stream(fields)
                .map(Field::getName)
                .toArray(String[]::new);

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for(Object object:objects){
            Object[] rowData= new Object[fields.length];
            int index = 0;
            for(Field field : fields){
                field.setAccessible(true);
                try{
                    rowData[index++] = field.get(object);
                } catch (IllegalAccessException e){
                    rowData[index++] = null;
                }
            }
            tableModel.addRow(rowData);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(new JTable(tableModel)), BorderLayout.CENTER);
        setMainPanel(panel);
    }
}

package com.abcjava.pos.controller;

import com.abcjava.pos.bo.BoFactory;
import com.abcjava.pos.bo.BoTypes;
import com.abcjava.pos.bo.custom.ItemBo;
import com.abcjava.pos.dto.ItemDTO;
import com.abcjava.pos.view.tm.ItemTm;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ItemManagementFormController {
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public JFXButton btnSaveItem;
    public TextField txtSearchItem;
    public TableView<ItemTm> tblItemDetails;
    public TableColumn<ItemTm,String> colCode;
    public TableColumn<ItemTm,String>  colDescription;
    public TableColumn <ItemTm,Double> colUnitPrice;
    public TableColumn<ItemTm,Button>  colOptions;
    public AnchorPane itemManagementContext;
    public TableColumn<ItemTm,Integer>  colQtyOnHand;
    private String text = "";

    private ItemBo itemBo = BoFactory.getInstance().getBo(BoTypes.ITEMS);

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colOptions.setCellValueFactory(new PropertyValueFactory<>("button"));

        txtSearchItem.textProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println(newValue);
            setDataToTable(newValue);
        });

        tblItemDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println( newValue.getDescription());
            if(null!= newValue){
                selectedTableRowToTextField(newValue);
            }

        });

        setDataToTable(text);
    }

    private void selectedTableRowToTextField(ItemTm itemTm) {
        txtCode.setText(itemTm.getCode());
        txtDescription.setText(itemTm.getDescription());
        txtUnitPrice.setText(String.valueOf(itemTm.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(itemTm.getQtyOnHand()));
        btnSaveItem.setText("Update Item");

    }


    public void btnBackToHome(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) itemManagementContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBordForm.fxml"))));
    }

    public void btnAddNewItem(ActionEvent actionEvent) {
        btnSaveItem.setText("save Item");
        clearField();

    }

    public void btnSaveItemOnAction(ActionEvent actionEvent) {
        if(btnSaveItem.getText().equalsIgnoreCase("Save Item")){ //save item
            setDataToDatabase();
            setDataToTable(text);
            clearField();
        }else{
            try {
               boolean isUpdatedItem = itemBo.updateItem(
                       new ItemDTO( txtCode.getText(),
                               txtDescription.getText(),
                               Double.parseDouble(txtUnitPrice.getText()),
                               Integer.parseInt(txtQtyOnHand.getText()))
               );
                if (isUpdatedItem) {
                    clearField();
                    new Alert(Alert.AlertType.INFORMATION, "Item Updated").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            setDataToTable(text);
            clearField();
        }

    }

    private void clearField() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }

    private void setDataToTable(String text) {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
        //search
        String searchText = "%"+text+"%";
        try{
            ArrayList<ItemDTO> itemArrayList = itemBo.searchItems(searchText);

           for(ItemDTO item : itemArrayList){
                Button button = new Button("Delete");
                ItemTm itemTm = new ItemTm(
                        item.getCode(),
                        item.getDescription(),
                        item.getUnitPrice(),
                        item.getQtyOnHand(),
                        button);
                tmList.add(itemTm);

                button.setOnAction(event -> {
                    // System.out.println(c.getName());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  want to delete this customer ?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {
                        try{
                            boolean isDeletedItem = itemBo.deleteItem(itemTm.getCode());
                            if(isDeletedItem ){
                                 setDataToTable(text);
                                new Alert(Alert.AlertType.INFORMATION, "Item Deleted").show();
                            }else {
                                new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();
                            }
                        }catch (ClassNotFoundException | SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        tblItemDetails.setItems(tmList);
        }



    private void setDataToDatabase() {
        try {
            boolean isSavedItem = itemBo.saveItem(
                    new ItemDTO(
                            txtCode.getText(),
                            txtDescription.getText(),
                            Double.parseDouble(txtUnitPrice.getText()),
                            Integer.parseInt(txtQtyOnHand.getText())
                    )
            );
            if (isSavedItem) {
                clearField();
                new Alert(Alert.AlertType.INFORMATION, "Item Saved").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}

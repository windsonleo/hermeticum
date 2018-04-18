package com.teccsoluction.hermeticum.conf;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Objects;

import org.slf4j.Logger;

import com.teccsoluction.hermeticum.entidade.Usuario;
import com.teccsoluction.hermeticum.view.FxmlView;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Manages switching Scenes on the Primary Stage
 */
public class StageManager {

    private static final Logger LOG = getLogger(StageManager.class);
    private final Stage primaryStage;
    private final SpringFXMLLoader springFXMLLoader;
    
    private  Usuario userStage;
    

    
    
    public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage) {
        this.springFXMLLoader = springFXMLLoader;
        this.primaryStage = stage;
        this.userStage = new Usuario();
        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setWidth(1200);
//        primaryStage.setHeight(700);
        
    }

    public void switchScene(final FxmlView view) {
    	
    	
    
    	Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
      
        show(viewRootNodeHierarchy, view.getTitle());
    }
    
    public void show(final Parent rootnode, String title) {
        
    	
    	Scene scene = prepareScene(rootnode);
    	
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);

        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
      
   
        
        try {
           
            primaryStage.show();
        } catch (Exception exception) {
            logAndExit ("Unable to show scene for title" + title,  exception);
        }
    }
    
    private Scene prepareScene(Parent rootnode){
      
    	
    	Scene scene = primaryStage.getScene();
     


        if (scene == null) {
        	
            scene = new Scene(rootnode);
            primaryStage.initStyle(StageStyle.UNDECORATED);
        }
        
        scene.setRoot(rootnode);
        
        return scene;
    }

    /**
     * Loads the object hierarchy from a FXML document and returns to root node
     * of that hierarchy.
     *
     * @return Parent root node of the FXML document hierarchy
     */
    private Parent loadViewNodeHierarchy(String fxmlFilePath) {
        Parent rootNode = null;
        try {
            rootNode = springFXMLLoader.load(fxmlFilePath);
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception exception) {
            logAndExit("Unable to load FXML view" + fxmlFilePath, exception);
        }
        return rootNode;
    }
    
    
    private void logAndExit(String errorMsg, Exception exception) {
        LOG.error(errorMsg, exception, exception.getCause());
        Platform.exit();
    }
    
    public Usuario GetaUsuarioStage(){
    	
//    	this.userStage = user;
    	
    	return userStage;
    }
    
    
    
    public void SetaUsuarioStage(Usuario user){
    	
    	this.userStage = user;
    	
    }
}

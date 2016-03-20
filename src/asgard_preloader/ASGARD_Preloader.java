/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asgard_preloader;

import javafx.application.Preloader;  
import javafx.geometry.Pos;
import javafx.scene.Scene;  
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;  
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;  
import javafx.stage.StageStyle;
  
public class ASGARD_Preloader extends Preloader {

    VBox vbox = new VBox();
    HBox hbox = new HBox();
    Label splashLbl = new Label("Asgard");
    Label versionLbl = new Label("Alpha Version");
    ProgressBar splashPgr = new ProgressBar();
    Stage stage;  
    boolean noLoadingProgress = true;  

    private Scene createPreloaderScene() {  
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);
        vbox.setStyle("-fx-background-color:radial-gradient(center 50% 50%,radius 200%,rgba(22,165,225,60)5%,rgba(17,129,175,100)25%);");

        splashLbl.setFont(new Font(75));
        splashLbl.setStyle("-fx-text-fill:white;"+
                "-fx-font-weight:bold");
        versionLbl.setFont(new Font(20));
        versionLbl.setStyle("-fx-text-fill:white;"+
                "-fx-font-weight:bold");

        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.setSpacing(5);

        splashPgr.setStyle("-fx-background-color:transparent");
        splashPgr.getStylesheets().addAll("resources/css/progressbar.css");

        hbox.getChildren().addAll(splashLbl,versionLbl);
        vbox.getChildren().addAll(hbox,splashPgr);
        return new Scene( vbox, 620, 300 );  
    }  

    @Override  
    public void start( Stage stage ) throws Exception {  
        this.stage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene( createPreloaderScene() );
        stage.show();  
    }  

    @Override  
    public void handleProgressNotification( Preloader.ProgressNotification pn ) {   
        if ( pn.getProgress() != 1.0 || !noLoadingProgress ) {  
            splashPgr.setProgress( pn.getProgress() / 2 );  
            if ( pn.getProgress() > 0 ) {  
                noLoadingProgress = false;  
            }  
        }  
    }  

    @Override  
    public void handleStateChangeNotification( Preloader.StateChangeNotification evt ) {  

    }  

    @Override  
    public void handleApplicationNotification( Preloader.PreloaderNotification pn ) {  
        if ( pn instanceof Preloader.ProgressNotification ) {  
            double v = ( ( Preloader.ProgressNotification ) pn ).getProgress();  
            if ( !noLoadingProgress ) {  
                v = 0.5 + v / 2;  
            }  
            splashPgr.setProgress(v);  
        }  
        else if ( pn instanceof Preloader.StateChangeNotification ) {  
            stage.hide();  
        }  
    }  
}  
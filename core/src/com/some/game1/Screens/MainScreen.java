package com.some.game1.Screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.MainComponents.Settings;
import com.some.game1.Entities.Technical.Texts.Event;
import com.some.game1.Strategy;

import java.util.ArrayList;

public class MainScreen extends ScreenBase {
    private Label[] mainInfo;
    private Table mainInfoTable;
    public static ArrayList<Event> events = new ArrayList<>();

    public MainScreen(Strategy strategy) {
        this.strategy = strategy;
        gov = strategy.getGov();

    }

    @Override
    public void showh() {


        if (Settings.events && events.size() > 0) {
            Event curEvent = events.get(0);
            events.remove(0);
            strategy.setScreen(new EventScreen(curEvent, gov, strategy));
        }

        mainInfoTable = new Table();
        stage.addActor(mainInfoTable);

        mainInfoTable.setFillParent(true);
        mainInfo = mainInfo();

        mainInfoTable.defaults().size(230, 50).fill();
        mainInfoTable.add(mainInfo);
        mainInfoTable.row();
        mainInfoTable.defaults().size(230, 30).fill();
        mainInfoTable.add(addButtons(skin));
        mainInfoTable.top().left();
        mainInfoTable.pad(50, 50, 100,100);
        Table endTurnTable = new Table();
        endTurnTable.setFillParent(true);
        endTurnTable.right().bottom();
        endTurnTable.pad(0,0,50,50);
        Button endButton = endTurnButton();
        endTurnTable.defaults().size(200, 100).fill();
        endTurnTable.add(endButton);
        stage.addActor(endTurnTable);
        mainInfoTable.debug();
        mainInfoTable.row();


        Label test = new Label("Some mes", skin);
        mainInfoTable.add(test);
        final Label next = new Label("Some mes", skin);
        mainInfoTable.add(next);
        next.setVisible(false);
        test.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                long time = System.currentTimeMillis();
                while (time + 500 > System.currentTimeMillis()) {
                    int i = 0;
                }
                next.setVisible(true);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                next.setVisible(false);
            }
        });
    }

    private Label[] mainInfo(){
        Label[] labels = new Label[4];
        labels[0] = new Label("Year " + (BS.turn / 12) + " month " +  (BS.turn % 12), skin);
        labels[1] = new Label("Money " + gov.getEconomy().getMoney(), skin);
        labels[2] = new Label("Taxes " + gov.getEconomy().getProfit(), skin);
        labels[3] = new Label("Influence " + gov.getInfluence(), skin);
        Table table = new Table();
        table.add(labels);
        return labels;
    }

    private Button endTurnButton(){
        TextButton button = new TextButton("Turn", skin);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                gov.turn(true);
                if (Settings.events && events.size() > 0 && BS.events_show) {
                    Event curEvent = events.get(0);
                    events.remove(0);
                    strategy.setScreen(new EventScreen(curEvent, gov, strategy));
                }
            }
        });
        return button;
    }

    private Button[] addButtons(Skin skin){
        Button[] buttons = new Button[5];
        TextButton button1 = new TextButton("region", skin);
        //button1.setColor((float) 20.0/255, (float) 190.0/255, (float) 1, (float) 1);
        buttons[0] = button1;
        button1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(new RegionsScreen(strategy));
            }
        });
        TextButton estateB = new TextButton("estate", skin);
        buttons[1] =(estateB);
        estateB.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(new EstateScreen(strategy));
            }
        });
        TextButton lawB = new TextButton("laws", skin);
        buttons[2] =(lawB);
        lawB.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(new LawsScreen(strategy));
            }
        });
        TextButton economyB = new TextButton("economy", skin);
        buttons[3] =(economyB);
        economyB.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(new EconomyScreen(gov, strategy));
            }
        });
        TextButton armyB = new TextButton("Army", skin);
        buttons[4] =(armyB);
        armyB.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(new ArmyScreen(gov, strategy));
            }
        });

        return buttons;
    }


    @Override
    public void renderh() {
        mainInfo[0].setText("Year " + (BS.turn / 12) + " month " +  (BS.turn % 12));
        mainInfo[1].setText("Money " + gov.getEconomy().getMoney());
        mainInfo[2].setText("Taxes " + gov.getEconomy().getProfit());
        mainInfo[3].setText("Influence " + gov.getInfluence());
    }


}

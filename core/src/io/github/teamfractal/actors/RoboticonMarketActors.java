/*
	www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
	This class has received large changes focused on making the layout of the Roboticon purchase screen more
	user-friendly.
 */

package io.github.teamfractal.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.teamfractal.RoboticonQuest;
import io.github.teamfractal.entity.Roboticon;
import io.github.teamfractal.entity.enums.ResourceType;
import io.github.teamfractal.screens.RoboticonMarketScreen;
import java.util.ArrayList;
import java.util.HashMap;

public class RoboticonMarketActors extends Table {
	private RoboticonQuest game;
	private RoboticonMarketScreen screen;
	private Integer roboticonAmount = 0;
	private int currentlySelectedRoboticonPos;
	private Texture roboticonTexture;
	private Label topText;
	private Label playerStats;
	private Label marketStats;
	private Label roboticonID;
	private Image roboticonImage = new Image();

	private static final Texture no_cust_texture;
	private static final Texture energy_texture;
	private static final Texture ore_texture;
	private static final Texture food_texture;
	private static final Texture no_robotic_texture;

	private ArrayList<Roboticon> roboticons = new ArrayList<Roboticon>();

	static {
		no_cust_texture = new Texture(Gdx.files.internal("roboticon_images/robot.png"));
		energy_texture = new Texture(Gdx.files.internal("roboticon_images/robot_energy.png"));
		ore_texture = new Texture(Gdx.files.internal("roboticon_images/robot_ore.png"));
		food_texture = new Texture(Gdx.files.internal("roboticon_images/robot_food.png"));
		no_robotic_texture = new Texture(Gdx.files.internal("roboticon_images/no_roboticons.png"));
	}

	public RoboticonMarketActors(final RoboticonQuest game, RoboticonMarketScreen screen) {
		this.game = game;
		this.screen = screen;

		this.roboticonID = new Label("", game.skin);
		this.marketStats = new Label("", game.skin);

		widgetUpdate();

		//UI elements
		final Label lblBuyRoboticon = new Label("PURCHASE ROBOTICONS HERE", game.skin);
		final Label lblRoboticons = new Label("Roboticons:", game.skin);
		final Label lblRoboticonAmount = new Label(roboticonAmount.toString(), game.skin);

		// Button to increase number of roboticons to be purchased
		final TextButton addRoboticonButton = new TextButton("+", game.skin);
		addRoboticonButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				roboticonAmount += 1;
				lblRoboticonAmount.setText(roboticonAmount.toString());
			}
		});

		// Button to decrease number of roboticons to be purchased
		final TextButton subRoboticonButton = new TextButton("-", game.skin);
		subRoboticonButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (roboticonAmount > 0) {
					roboticonAmount -= 1;
					lblRoboticonAmount.setText(roboticonAmount.toString());
				}
			}
		});

		// Button to buy the selected amount of roboticons from the market
		final TextButton buyRoboticonsButton = new TextButton("Buy Roboticons (Unit Cost: " + (Integer.toString(game.market.getSellPrice(ResourceType.ROBOTICON))) + ")", game.skin);
		buyRoboticonsButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.getPlayer().purchaseRoboticonsFromMarket(roboticonAmount, game.market);
				roboticonAmount = 0;
				lblRoboticonAmount.setText(roboticonAmount.toString());
				widgetUpdate();
			}
		});

		// Current Roboticon Text
		String playerRoboticonText = "CUSTOMISE PLAYER " + (game.getPlayerInt() + 1) + "'S ROBOTICONS HERE";
		final Label lblCurrentRoboticon = new Label(playerRoboticonText, game.skin);


		// Buttons to move backwards and forwards in the player's roboticon inventory
		final TextButton moveLeftRoboticonInventoryBtn = new TextButton("<", game.skin);
		moveLeftRoboticonInventoryBtn.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (currentlySelectedRoboticonPos > 0) {
					currentlySelectedRoboticonPos--;
					setCurrentlySelectedRoboticon(currentlySelectedRoboticonPos);
				}
			}
		});

		final TextButton moveRightRoboticonInventoryBtn = new TextButton(">", game.skin);
		moveRightRoboticonInventoryBtn.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (currentlySelectedRoboticonPos < roboticons.size() - 1) {
					currentlySelectedRoboticonPos++;
					setCurrentlySelectedRoboticon(currentlySelectedRoboticonPos);
				}
			}
		});


		// Purchase Customisation Text
		final Label lblPurchaseCustomisation = new Label("Customisation Type:", game.skin);

		// Drop down menu to select the customisation type for the selected roboticon
		final SelectBox<String> customisationDropDown = new SelectBox<String>(game.skin);
		String[] customisations = {"Energy", "Ore", "Food"};
		customisationDropDown.setItems(customisations);

		// Button to buy the selected customisation and customise the selected roboticon
		final TextButton buyCustomisationButton = new TextButton("Buy Roboticon Customisation (Cost: " + Integer.toString(game.market.getSellPrice(ResourceType.CUSTOMISATION)) + ")", game.skin);
		buyCustomisationButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (-1 == currentlySelectedRoboticonPos) {
					// nothing selected.
					return;
				}
				HashMap<String, ResourceType> converter = new HashMap<String, ResourceType>();
				converter.put("Energy", ResourceType.ENERGY);
				converter.put("Ore", ResourceType.ORE);
				converter.put("Food", ResourceType.FOOD);
				Roboticon roboticonToCustomise = roboticons.get(currentlySelectedRoboticonPos);

				game.getPlayer().purchaseCustomisationFromMarket(converter.get(customisationDropDown.getSelected()), roboticonToCustomise, game.market);
				widgetUpdate();
			}
		});

		//Align the window objects to the center of the screen.
		align(Align.center);

		add(lblBuyRoboticon);

		row();

		Table row2 = new Table();
		row2.add(lblRoboticons);
		row2.add(subRoboticonButton).width(50);
		row2.add(lblRoboticonAmount).width(30);
		row2.add(addRoboticonButton);
		add(row2).expand();

		row();

		add(buyRoboticonsButton);

		row();

		add(marketStats);

		//Gap between the sections
		row();
		add().height(20);
		row();

		//Row 5
		add(lblCurrentRoboticon);

		row();

		//Row 6
		add(roboticonImage);

		row();

		//Row 7
		Table row7 = new Table();
		row7.add(moveLeftRoboticonInventoryBtn).left().width(20);
		row7.add(roboticonID).center().align(Align.center);
		row7.add(moveRightRoboticonInventoryBtn).right().width(20);
		add(row7);

		row();

		//Row 8
		add(lblPurchaseCustomisation);

		row();

		//Row 9
		add(customisationDropDown);

		row();

		//Row 10
		add(buyCustomisationButton);

		row().height(20);
		row();

		final TextButton nextButton = new TextButton("Next ->", game.skin);
		nextButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.nextPhase();
			}
		});
		screen.getStage().addActor(nextButton);
		nextButton.setPosition(Gdx.app.getGraphics().getWidth() - nextButton.getWidth() - 10, 10);


	}

	/**
	 * Add a number of 0s to the start of a number
	 *
	 * @param number the number to pad with 0s
	 * @param length the length of zeros to append to the number
     * @return a string containing a number (length) of zeros followed by the number
     */
	public String padZero(int number, int length) {
		String s = "" + number;
		while (s.length() < length) {
			s = "0" + s;
		}
		return s;
	}

	/**
	 * Get the roboticon selected by its position in the list.
	 *
	 * @param roboticonPos The position of the roboticon in the roboticon list.
     */
	public void setCurrentlySelectedRoboticon(int roboticonPos) {
		if (roboticonPos != -1) {

			ResourceType roboticonType = roboticons.get(roboticonPos).getCustomisation();

			switch (roboticonType) {
				case Unknown:
					roboticonTexture = no_cust_texture;
					break;
				case ENERGY:
					roboticonTexture = energy_texture;
					break;
				case ORE:
					roboticonTexture = ore_texture;
					break;
				case FOOD:
					roboticonTexture = food_texture;
				default:
					break;
			}

			int id = roboticons.get(roboticonPos).getID();
			this.roboticonID.setText("Roboticon Issue Number: " + padZero(id, 4));

		} else {
			roboticonTexture = no_robotic_texture;
			this.roboticonID.setText("Roboticon Issue Number: ####");
		}

		roboticonImage.setDrawable(new TextureRegionDrawable(new TextureRegion(roboticonTexture)));
	}

	/**
	 * Manually update relevant UI elements to reflect changes.
	 */
	public void widgetUpdate() {
		roboticons.clear();
		for (Roboticon r : game.getPlayer().getRoboticons()) {
			if (!r.isInstalled()) {
				roboticons.add(r);
			}
		}

		// Draws turn and phase info on screen
		if (this.topText != null) this.topText.remove();
		String phaseText = game.getPlayer().getName() + "; Phase " + game.getPhase();
		this.topText = new Label(phaseText, game.skin);
		topText.setWidth(120);
		topText.setPosition(screen.getStage().getWidth() / 2 - 40, screen.getStage().getViewport().getWorldHeight() - 20);
		screen.getStage().addActor(topText);

		// Draws player stats on screen
		if (this.playerStats != null) this.playerStats.remove();
		String statText = "Ore: " + game.getPlayer().getOre() + " Energy: " + game.getPlayer().getEnergy() + " Food: "
				+ game.getPlayer().getFood() + " Money: " + game.getPlayer().getMoney();
		this.playerStats = new Label(statText, game.skin);
		playerStats.setWidth(250);
		playerStats.setPosition(0, screen.getStage().getViewport().getWorldHeight() - 20);
		screen.getStage().addActor(playerStats);

		if (roboticons.size() == 0) {
			currentlySelectedRoboticonPos = -1;
		} else if (currentlySelectedRoboticonPos == -1) {
			currentlySelectedRoboticonPos = 0;
		}

		setCurrentlySelectedRoboticon(currentlySelectedRoboticonPos);
		
		marketStats.setText("Market - Roboticons: " + game.market.getResource(ResourceType.ROBOTICON));

	}

}

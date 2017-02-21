/*
	www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
	This class is new for assessment 3 in order to implement the auction system.
 */
package io.github.teamfractal.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.github.teamfractal.Auction;
import io.github.teamfractal.RoboticonQuest;
import io.github.teamfractal.exception.NotEnoughMoneyException;
import io.github.teamfractal.exception.NotEnoughResourceException;
import io.github.teamfractal.screens.AuctionScreen;
import io.github.teamfractal.screens.MarketScreen;
import io.github.teamfractal.util.AuctionBid;
import io.github.teamfractal.util.AuctionableItem;

public class AuctionActors extends Table {
	private Auction auction;
	private RoboticonQuest game;
	private Label auctionTitle;
	private Label bidTitle;
	private Label putUpItemTitle;
	private Label bidAmountPounds;
	private TextButton placeBid;
	private SelectBox<String> itemsUpForBiddingSelectBox;
	private TextField bidAmount;
	private TextButton returnButton;
	
	private SelectBox<String> auctionableItemsSelectBox;
	private TextField auctionItemAmount;
	private TextButton auctionItemButton;

	/**
	 * The Auction actors
	 * For building the interface for the auction system..
	 *
	 * @param game    The RoboticonQuest object representing the game.
	 * @param screen   The AuctionScreen containing the actors.
	 * @param marketScreen  The MarketScreen to return to when leaving the AuctionScreen.
	 */
	public AuctionActors(final RoboticonQuest game, AuctionScreen screen, final MarketScreen marketScreen) {
		center();
		Skin skin = game.skin;
		this.game = game;
		auction = game.auction;
		
		// Create UI Components
		auctionTitle = new Label("Auction: ", skin);
		bidTitle = new Label("Place a bid: ", skin);
		itemsUpForBiddingSelectBox = new SelectBox<String>(skin);
		bidAmount = new TextField("0", skin);
		bidAmountPounds = new Label("£", skin);
		placeBid = new TextButton("Place Bid", skin);
		putUpItemTitle = new Label("Put an item up for Auction:", skin);
		auctionableItemsSelectBox = new SelectBox<String>(skin);
		auctionItemButton = new TextButton("Auction Item", skin);
		auctionItemAmount = new TextField("1", skin);

		returnButton = new TextButton("Back to the Market Menu", skin);
		returnButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(marketScreen);
			}
		});
		
		TextFieldFilter digitFilter = new TextFieldFilter() {
		    public  boolean acceptChar(TextField textField, char c) {
				return Character.isDigit(c);
			}
		};
		
		auctionItemAmount.setTextFieldFilter(digitFilter);
		bidAmount.setTextFieldFilter(digitFilter);
		
		itemsUpForBiddingSelectBox.setItems(getCurrentAuctionItemsStrings());
		auctionableItemsSelectBox.setItems(getCurrentPlayerAuctionableItemStrings());

		// Adjust properties.
		auctionTitle.setAlignment(Align.left);
		bidTitle.setAlignment(Align.left);
		putUpItemTitle.setAlignment(Align.left); 
		
		// Add UI components to screen.
		add(auctionTitle);
		rowWithHeight(40);

		// Setup UI Layout.
		add(bidTitle);
		rowWithHeight(10);
		add(itemsUpForBiddingSelectBox);
		add().spaceRight(20);
		add(bidAmountPounds);
		add(bidAmount).width(40);
		add().spaceRight(10);
		add(placeBid);
		
		rowWithHeight(30);
		
		add(putUpItemTitle);
		rowWithHeight(10);
		add(auctionableItemsSelectBox);
		add().spaceRight(20);
		add();
		add(auctionItemAmount).width(40);
		add().spaceRight(20);
		add(auctionItemButton);

		row();
		add().height(10);
		row();
		add(returnButton);

		pad(20);
		
		bindEvents();
	}
	
	/**
	 * Bind button events.
	 *
	 * @return none
	 */
	private void bindEvents() {
		auctionItemButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Object objectToAuction = auction.getPlayerAuctionableItems(
						game.getPlayer())[auctionableItemsSelectBox.getSelectedIndex()];
				
				try {
					AuctionableItem itemToAuction = new AuctionableItem(objectToAuction,		//Resources checked for <= player resources
							Integer.parseInt(auctionItemAmount.getText()), game.getPlayer());	// in the AuctionableItem constructor
					
					auction.addItemToAuction(itemToAuction);
					
					widgetUpdate(true);
				} catch (NotEnoughResourceException e) {
					// TODO: handle exception
				}
			}
		});
		
		placeBid.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(itemsUpForBiddingSelectBox.getItems().size > 0) {
					try {
						AuctionBid bid = new AuctionBid(Integer.parseInt(bidAmount.getText()), game.getPlayer());
						auction.getAuctionItemAtIndex(itemsUpForBiddingSelectBox.getSelectedIndex(),
								game.getPlayer()).placeBid(bid);

						widgetUpdate(false);
					} catch (NotEnoughMoneyException e) {
						// TODO: handle exception
					}
				}
			}
		});
		
		auctionableItemsSelectBox.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor){
				if (auctionableItemsSelectBox.getSelectedIndex() > 2){
					auctionItemAmount.setVisible(false);	//Hide the quantity box if the selected item is not a
															//resource.
				}
				else{
					auctionItemAmount.setVisible(true);
				}
			}
		});
	}

	/**
	 * Updates the UI elements after a change has been made to the state of the game - called manually when required.
	 *
	 * @param doDisablePuttingItemsUpForAuction
     */
	public void widgetUpdate(boolean doDisablePuttingItemsUpForAuction) {
		if(doDisablePuttingItemsUpForAuction){
			putUpItemTitle.setText("You have already put up an item for auction this turn.");
			auctionableItemsSelectBox.setVisible(false);
			auctionItemAmount.setVisible(false);
			auctionItemButton.setVisible(false);
		}
		
		itemsUpForBiddingSelectBox.setItems(getCurrentAuctionItemsStrings());
	}

	/**
	 * Get string representations from the auctionableObjects array.
	 *
	 * @return a string array of the current auctionable items from the auctionableObjects array.
     */
	private String[] getCurrentPlayerAuctionableItemStrings() {
		Object[] auctionableObjects = auction.getPlayerAuctionableItems(game.getPlayer());
		String[] strings = new String[auctionableObjects.length];
		
		for (int i = 0; i < strings.length; i++) {
			strings[i] = auctionableObjects[i].toString();
		}
		
		return strings;
	}

	/**
	 * Get string representations of the items currently up for auction.
	 *
	 * @return a string array of the current items up for auction.
     */
	private String[] getCurrentAuctionItemsStrings() {
		Object[] auctionItems = auction.getAuctionItems(game.getPlayer());
		String[] strings = new String[auctionItems.length];
		
		for (int i = 0; i < strings.length; i++) {
			strings[i] = auctionItems[i].toString();
		}
		
		return strings;
	}
	
	/**
	 * Add an empty row to current table.
	 * @param height  The height for that empty row.
	 */
	private void rowWithHeight(int height) {
		row();
		add().spaceTop(height);
		row();
	}
}
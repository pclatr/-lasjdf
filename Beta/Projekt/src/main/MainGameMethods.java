package main;

import main.alertBox.AlertBox;
import main.standardMethods.SortDices;
import userInterface.MainController;


/**
 * The MainGame class provides the main algorithms for the game.
 * 
 * @author Johannes Seith and Jakob Seith
 *
 */
public class MainGameMethods {
	// Checkboxen to hold a dice
	
	

	public static int[] currentDices = new int[6];

	// Constructor.
	public MainGameMethods() {
	}

	/**
	 * Here is the algorithm to create the dices and also to check up the hold up
	 * dices.
	 */
	public void generateDices(boolean[] checkBoxValues) {

		if (MainController.tries != 0) {

			if (checkHoldDices(checkBoxValues,currentDices)) {
				
				// If you have hold up a dice, its value mustn't change.
				for (int i = 0; i < currentDices.length; i++) {
					if (!checkBoxValues[i]) {
						currentDices[i] = (int) (Math.random() * 6 + 1);
					}
				}
				MainController.tries--;

				currentDices = sortDices(currentDices,checkBoxValues);

			} else {
				new AlertBox().information("Hinweis!", "Das geht nicht!");
			}

		} else {
			new AlertBox().information("Ey du Arsch!", "Deine Versuche sind aufgebraucht du Dummbeudel!");
		}

	}

	/**
	 * It is an easy sort algorithm and necessary to be improved.
	 * 
	 * @param unsortedDices
	 *            you give the method an unsorted list of dices,
	 * @return and you will get a sort list.
	 */
	private int[] sortDices(int[] unsortedDices,boolean[] checkBoxValues) {
		int hold;
		for (int i = 0; i < unsortedDices.length; i++) {
			for (int j = unsortedDices.length - 1; j > 0; j--) {
				if (!checkBoxValues[j]) {
					if (unsortedDices[j - 1] > unsortedDices[j] && !checkBoxValues[j - 1]) {
						hold = unsortedDices[j];
						unsortedDices[j] = unsortedDices[j - 1];
						unsortedDices[j - 1] = hold;
					}
				}
			}
		}
		return unsortedDices;

	}

	/**
	 * Check up if the combination of the hold up dices are allowed.
	 * 
	 * @param checkBoxList
	 *            this method needs the current list of checkboxes
	 * @return true or false
	 */
	public boolean checkHoldDices(boolean[] checkBoxList,int[] currentDices) {

		int[] dices = new int[6];
		// All not hold up dices are 0 in the dice array. It makes the to test among of
		// combinations very smaller.
		for (int i = 0; i < dices.length; i++) {
			if (checkBoxList[i] == true) {
				dices[i] = currentDices[i];
			}
		}
		// Algorithm to put the zeros at the beginning. It allows to suspend
		// combinations.
		new SortDices().sortDices(dices);

		// Search the position of the first set dice.
		int positonOfFirstSetDice = 0;
		while (positonOfFirstSetDice < dices.length) {
			if (dices[positonOfFirstSetDice] != 0 && positonOfFirstSetDice < 5) {
				break;
			} else {
				if (positonOfFirstSetDice == 5 && dices[5] != 0) {
					return false;
				} else {
					positonOfFirstSetDice++;
				}
			}
		}
		// Is the position of the first set dice bigger then 5, no dice is was set and
		// that is always true.
		if (positonOfFirstSetDice > 5) {
			return true;
		} else {
			// Divided between a hold up street or pair.
			if (dices[positonOfFirstSetDice] != dices[positonOfFirstSetDice + 1]) {

				// Comparision of a street.
				while (positonOfFirstSetDice < dices.length) {
					if ((dices[positonOfFirstSetDice] + 1) == dices[positonOfFirstSetDice + 1]) {
						if (positonOfFirstSetDice < 4) {
							positonOfFirstSetDice++;
						} else {
							return true;
						}
					} else {
						return false;
					}
				}
			} else {

				// Comparision between a pair and a pair street.
				while (positonOfFirstSetDice < dices.length) {
					if (dices[positonOfFirstSetDice] == dices[positonOfFirstSetDice + 1]) {
						if (positonOfFirstSetDice == 4) {
							return true;
						} else {
							if (dices[positonOfFirstSetDice + 1] == dices[positonOfFirstSetDice + 2]) {
								if (positonOfFirstSetDice == 3) {
									return true;
								} else {
									positonOfFirstSetDice++;
								}
							} else {
								if (positonOfFirstSetDice < 3) {
									positonOfFirstSetDice = positonOfFirstSetDice + 2;
								} else {
									return false;
								}
							}
						}
					} else {
						return false;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Algorithm to evaluate the points the current player has to get.
	 * @param tries is necessary to evaluate to correct value of points.
	 * @return the value of points 
	 */
	public int evaluatePoints(int tries) {

		int[] dices = currentDices;
		int hold;
		for (int i = 0; i < dices.length; i++) {
			for (int j = dices.length - 1; j > 0; j--) {

				if (dices[j - 1] > dices[j]) {
					hold = dices[j];
					dices[j] = dices[j - 1];
					dices[j - 1] = hold;
				}

			}
		}

		checkStreet(tries, dices);

		// Check up if all dices have the same value.
		if (dices[0] == dices[1]) {
			if (dices[1] == dices[2]) {
				if (dices[2] == dices[3]) {
					if (dices[3] == dices[4]) {
						if (dices[4] == dices[5]) {
							
							//All have the same value.
							if (tries == 2) {
								return 5;
							}
							if (tries == 1) {
								return 4;
							} else {
								return 3;
							}
						} else {
							
							// 1 2 3 4 5 are the same. 6 is single.
						}
					} else {
						if (dices[4] == dices[5]) {
							
							// 1 2 3 4 are the same. 5 6 are single.
							if (tries == 2) {
								return 3;
							}
							if (tries == 1) {
								return 2;
							} else {
								return 1;
							}
						} else {
							
							//1 2 3 4 are the same. 5 6 are single.
						}
					}
				} else {
					if (dices[3] == dices[4]) {
						if (dices[4] == dices[5]) {
							
							//1 2 3 are the same. 4 5 6 are the same.
							if (tries == 2) {
								return 3;
							}
							if (tries == 1) {
								return 2;
							} else {
								return 1;
							}
						} else {
							
							// 1 2 3 are the same. 4 5 are the same. 6 is single.
						}
					} else {
						if (dices[4] == dices[5]) {
						
							// 1 2 3 are the same. 4 is single. 5 6 are the same.
						} else {
							
							//1 2 3 are the same. 4 5 6 are single.
						}
					}
				}
			} else {
				if (dices[2] == dices[3]) {
					if (dices[3] == dices[4]) {
						if (dices[4] == dices[5]) {
							
							//1 2 are the same. 3 4 5 6 are the same.
							if (tries == 2) {
								return 3;
							}
							if (tries == 1) {
								return 2;
							} else {
								return 1;
							}
						} else {
						
							// 1 2 are the same. 3 4 5 are the same. 6 is single.
						}
					} else {
						if (dices[4] == dices[5]) {
							
							
							// 1 2 are the same. 3 4 are the same. 5 6 are the same.
							if (checkStreet(tries, dices) == 5 || checkStreet(tries, dices) == 4
									|| checkStreet(tries, dices) == 3) {
								return checkStreet(tries, dices);
							}
							if (tries == 2) {
								return 3;
							}
							if (tries == 1) {
								return 2;
							} else {
								return 1;
							}
						} else {
							
							// 1 2 are the same. 3 4 5 are the same. 6 is single.
						}
					}
				} else {
					if (dices[3] == dices[4]) {
						if (dices[4] == dices[5]) {
						
							// 1 2 are the same. 3 is single. 4 5 6 are the same.
						} else {
							
							// 1 2 are the same. 3 6 are single. 4 5 are the same.
						}
					} else {
						if (dices[4] == dices[5]) {
							
							//  1 2 are the same. 3 4 are single. 5 6 are the same.
						} else {
							
							// 1 2 are the same. 3 4 5 6 are the same.
							return checkStreet(tries, dices);
						}
					}
				}
			}

		} else {
			if (dices[1] == dices[2]) {
				if (dices[2] == dices[3]) {
					if (dices[3] == dices[4]) {
						if (dices[4] == dices[5]) {
							
							// 1 are single. 2 3 4 5 6 are the same.
						} else {
							
							//1 6 are single. 2 3 4 5 are the same.
						}
					} else {
						if (dices[4] == dices[5]) {//
							
							// 1 are single. 2 3 4 are the same. 5 6 are the same.
						} else {
						
							// 1 5 6 are single. 2 3 4 are the same.
						}
					}
				} else {
					if (dices[3] == dices[4]) {
						if (dices[4] == dices[5]) {
							
							// 1 2 3 are the same. 4 5 6 are the same.
						} else {
							
							// 2 3 are the same. 4 5 are the same. 1 6 are single.
						}
					} else {
						if (dices[4] == dices[5]) {
							
							// 1 are single. 2 3 are the same. 4 are single. 5 6 are the same.
						} else {
							
							// 1 4 5 6 are single. 2 3 are the same.
							return checkStreet(tries, dices);
						}
					}
				}
			} else {
				if (dices[2] == dices[3]) {
					if (dices[3] == dices[4]) {
						if (dices[4] == dices[5]) {
						
							// 1 2 are single. 3 4 5 6 are the same.
						} else {
						
							// 1 2 6 are single. 3 4 5 are the same.
						}
					} else {
						if (dices[4] == dices[5]) {
							
							// 1 2 are single. 3 4 are the same. 5 6 are the same.
						} else {
							
							// 1 2 5 6 are single. 3 4 are the same.
							return checkStreet(tries, dices);
						}
					}
				} else {
					if (dices[3] == dices[4]) {
						if (dices[4] == dices[5]) {
						
							// 1 2 3 are single. 4 5 6 are the same.
						} else {
							
							//1 2 3 6 are single. 4 5 are the same.
							return checkStreet(tries, dices);
						}
					} else {
						if (dices[4] == dices[5]) {
							
							// 1 2 3 4 are single. 5 6 are the same.
							return checkStreet(tries, dices);
						} else {
							
							// 1 2 3 4 5 6 are single.
							if (tries == 2) {
								return 5;
							} else if (tries == 1) {
								return 4;
							} else {
								return 3;
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
	/**
	 * Algorithm to check up the dices for a street.
	 * @param tries necessary to give back the correct value,
	 * @param sortedDices needs a sorted list of dices.
	 * @return the value of the points.
	 */
	private int checkStreet(int tries, int[] sortedDices) {

		if ((sortedDices[0] == sortedDices[1]) && ((sortedDices[1] + 1) == sortedDices[2])
				&& (sortedDices[2] == sortedDices[3]) && ((sortedDices[3] + 1) == sortedDices[4])
				&& (sortedDices[4] == sortedDices[5])) {
			if (tries == 2) {
				return 5;
			}
			if (tries == 1) {
				return 4;
			}
			if (tries == 0) {
				return 3;
			}
		}
		if (sortedDices[0] == sortedDices[1]) {//
			if ((sortedDices[1] + 1) == sortedDices[2]) {
				if ((sortedDices[2] + 1) == sortedDices[3]) {
					if ((sortedDices[3] + 1) == sortedDices[4]) {
						if ((sortedDices[4] + 1) == sortedDices[5]) {
							if (tries == 2) {
								return 3;
							}
						}
					}
				}
			}
		} else {
			if ((sortedDices[0] + 1) == sortedDices[1]) {//
				if (sortedDices[1] == sortedDices[2]) {
					if ((sortedDices[2] + 1) == sortedDices[3]) {
						if ((sortedDices[3] + 1) == sortedDices[4]) {
							if ((sortedDices[4] + 1) == sortedDices[5]) {
								if (tries == 2) {
									return 3;
								}
							}
						}
					}
				}
				if ((sortedDices[0] + 1) == sortedDices[1]) {//
					if ((sortedDices[1] + 1) == sortedDices[2]) {
						if (sortedDices[2] == sortedDices[3]) {
							if ((sortedDices[3] + 1) == sortedDices[4]) {
								if ((sortedDices[4] + 1) == sortedDices[5]) {
									if (tries == 2) {
										return 3;
									}
								}
							}
						}
					}
				}
				if ((sortedDices[0] + 1) == sortedDices[1]) {//
					if ((sortedDices[1] + 1) == sortedDices[2]) {
						if ((sortedDices[2] + 1) == sortedDices[3]) {
							if (sortedDices[3] == sortedDices[4]) {
								if ((sortedDices[4] + 1) == sortedDices[5]) {
									if (tries == 2) {
										return 3;
									}
								}
							}
						}
					}
					if ((sortedDices[0] + 1) == sortedDices[1]) {//
						if ((sortedDices[1] + 1) == sortedDices[2]) {
							if ((sortedDices[2] + 1) == sortedDices[3]) {
								if ((sortedDices[3] + 1) == sortedDices[4]) {
									if (sortedDices[4] == sortedDices[5]) {
										if (tries == 2) {
											return 3;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return 0;
	}

}

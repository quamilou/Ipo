package graphicalElements;

import gameCommons.IFrog;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'élément aux éléments é afficher
	 * @param e
	 */
    public void add(Element e);
    
    /**
     * Enléve tous les éléments actuellement affichés
     */
    public void clear();
    
    /***
     * Actualise l'affichage
     */
    public void repaint();
    
    /**
     * Lie la grenouille é l'environneemnt graphique
     * @param frog
     */
    public void setFrog(IFrog frog);
    
    /**
     * Lance un écran de fin de partie
     * @param message le texte é afficher
     */
    public void endGameScreen(String message);
}

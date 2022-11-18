package model;

public class ArtistUser extends ProducerUser{

    private int ammountOfSells;
    private int valueOfTotalSells;
    /**
     * @param name
     * @param imageUrl
     */
    public ArtistUser(String name, String imageUrl){
        super(name, imageUrl);
        this.ammountOfSells = 0;
        this.valueOfTotalSells = 0;
    }

    public void addAmmountOfSells(double cost){
        ammountOfSells++;
        valueOfTotalSells += cost;
    }

    public int getAmmountOfSells() {
        return ammountOfSells;
    }

    public int getValueOfTotalSells() {
        return valueOfTotalSells;
    }

}
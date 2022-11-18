package model;

public class Podcast extends Audio{

    private String description;
    private Category category;

    /**
     * @param name
     * @param url
     * @param duration
     * @param durationInSeconds
     * @param description
     * @param category
     */
    public Podcast(String name, String creator, String url, String duration, String description, int category){
        super(name, url, duration, creator);
        this.description = description;
        switch(category){
            case 1 -> this.category = Category.POLITICS;
            case 2 -> this.category = Category.ENTERTAINMENT;
            case 3 -> this.category = Category.VIDEOGAMES;
            case 4 -> this.category = Category.FASHION;
        }
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}

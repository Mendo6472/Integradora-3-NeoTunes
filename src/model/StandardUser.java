package model;

public class StandardUser extends ConsumerUser{

    private final int MAX_PLAYLISTS = 20;
    private final int MAX_DOWNLOADS = 100;

    public StandardUser(String nickName, String id){
        super(nickName, id);
    }



}
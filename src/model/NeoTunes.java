package model;
import java.util.ArrayList;

public class NeoTunes {

    private ArrayList<ConsumerUser> consumersUsers;
    private ArrayList<ProducerUser> producersUsers;

    public NeoTunes(){
        consumersUsers = new ArrayList<ConsumerUser>();
        producersUsers = new ArrayList<ProducerUser>();
    }

    public String registerUser(String nickName, String id){
        String msj = "El usuario se a añadido con exito";
        if(searchNickNamePos(nickName) != -1){
            return msj = "El NickName ya existe.";
        }
        ConsumerUser user = new StandardUser(nickName, id);
        consumersUsers.add(user);
        return msj;
    }

    public String upgradeUser(String nickName){
        String msj = "";
        int userPos = searchNickNamePos(nickName);
        if(userPos == -1){
            return msj = "No existe usuario con ese NickName";
        }
        if(consumersUsers.get(userPos) instanceof PremiumUser){
            return msj = "El usuario ya es premium";
        }
        String id = consumersUsers.get(userPos).getId();
        ConsumerUser user = new PremiumUser(nickName, id);
        consumersUsers.set(userPos, user);
        msj = "El usuario se a cambiado a premium exitosamente";
        return msj;
    }

    public int searchNickNamePos(String nickName){
        int pos = -1;
        boolean found = false;
        for(int i = 0; i < consumersUsers.size() && !found; i++){
            if(consumersUsers.get(i).getNickName().equals(nickName)){
                pos = i;
                found = true;
            }
        }
        return pos;
    }

}
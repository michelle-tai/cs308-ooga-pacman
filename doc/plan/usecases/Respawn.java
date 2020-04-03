public class Respawn{

    private HashSet<Character> inGameCharacters

    public Respawn(){
        for(Character charObj : inGameCharacters){
            if(charObj.getStatus().equals("respawn")){
                charObj.setHome();
            }
        }
    }
}
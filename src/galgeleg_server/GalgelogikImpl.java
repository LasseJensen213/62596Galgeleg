package galgeleg_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import brugerautorisation.transport.soap.Brugeradmin;
import dao.DAOException;
import dao.HighscoreDTO;
import dao.HighscoreDAO;
import dao.HighscoreDAOImpl;

public class GalgelogikImpl extends UnicastRemoteObject implements GalgelogikI {

    /**
     * AHT afprøvning er muligeOrd synlig på pakkeniveau
     */
    ArrayList<String> muligeOrd = new ArrayList<String>();
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    private long timeOfLastCommunication;

    @Override
    public boolean auth(String username, String password) {
        try {
            System.out.println("Login attempt: " + username + ":" + password);

            /*
            if(username.equals("test") && password.equals("test"))
                return true;
             */
            URL url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
            QName qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
            Service service = Service.create(url, qname);
            Brugeradmin ba = service.getPort(Brugeradmin.class);

            ba.hentBruger(username, password);
            return true;

        } catch (Exception ex) {
            System.out.println("No");
            return false;
        }
    }

    @Override
    public ArrayList<String> getBrugteBogstaver(String identifier) {
        return brugteBogstaver;
    }

    @Override
    public String getSynligtOrd(String identifier) {
        return synligtOrd;
    }

    @Override
    public String getOrdet(String identifier) {
        return ordet;
    }

    @Override
    public int getAntalForkerteBogstaver(String identifier) {
        return antalForkerteBogstaver;
    }

    @Override
    public boolean erSidsteBogstavKorrekt(String identifier) {
        return sidsteBogstavVarKorrekt;
    }

    @Override
    public boolean erSpilletVundet(String identifier) {
        return spilletErVundet;
    }

    @Override
    public boolean erSpilletTabt(String identifier) {
        return spilletErTabt;
    }

    @Override
    public boolean erSpilletSlut(String identifier) {
        return spilletErTabt || spilletErVundet;
    }

    public GalgelogikImpl(String identifier) throws java.rmi.RemoteException {
        timeOfLastCommunication = System.currentTimeMillis();

        try {
            /*
            muligeOrd.add("bil");
            muligeOrd.add("computer");
            muligeOrd.add("programmering");
            muligeOrd.add("motorvej");
            muligeOrd.add("busrute");
            muligeOrd.add("gangsti");
            muligeOrd.add("skovsnegl");
            muligeOrd.add("solsort");
            muligeOrd.add("seksten");
            muligeOrd.add("sytten");
            */
            hentOrdFraDr(identifier);
        } catch (IOException ex) {
            Logger.getLogger(GalgelogikImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        nulstil(identifier);
    }

    protected void updateTimeOfLastCommunication() {
        timeOfLastCommunication = System.currentTimeMillis();
    }

    protected long getTimeOfLastCommunication() {
        return timeOfLastCommunication;
    }

    @Override
    public void nulstil(String identifier) {
        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErVundet = false;
        spilletErTabt = false;
        ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
        opdaterSynligtOrd(identifier);
    }

    @Override
    public void opdaterSynligtOrd(String identifier) {
        synligtOrd = "";
        spilletErVundet = true;
        for (int n = 0; n < ordet.length(); n++) {
            String bogstav = ordet.substring(n, n + 1);
            if (brugteBogstaver.contains(bogstav)) {
                synligtOrd = synligtOrd + bogstav;
            } else {
                synligtOrd = synligtOrd + "*";
                spilletErVundet = false;
            }
        }
        HighscoreDAO highscoreDAO = new HighscoreDAOImpl("url","user","pass");
        int difficulty = calculateDifficulty(identifier);
        HighscoreDTO highscore = new HighscoreDTO("s165221", difficulty);
        
        try {
			highscoreDAO.addScore(highscore);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    private int calculateDifficulty(String word) {
        char[] charArray = word.toCharArray();

        int difficulty = 0;
        for(char c : charArray)
        {
            difficulty += charFrequency(c);
        }

        return difficulty;
    }

    private int charFrequency(char c) {
        char[] point1 = {'e','a','n','r'};
        char[] point2 = {'d','l','o','s','t'};
        char[] point3 = {'b','i','k','f','g','m','u','v'};
        char[] point4 = {'h','j','p','u','æ','ø','å'};
        char[] point8 = {'c','x','z'};
        char[] point10 = {'q','w'};

        for(char character : point1) {
            if (character==c){
                return 1;
            }
        }
        for(char character : point2) {
            if (character==c){
                return 2;
            }
        }
        for(char character : point3) {
            if (character==c){
                return 3;
            }
        }
        for(char character : point4) {
            if (character==c){
                return 4;
            }
        }
        for(char character : point8) {
            if (character==c){
                return 8;
            }
        }
        for(char character : point10) {
            if (character==c){
                return 10;
            }
        }
        return 0;
    }

    @Override
    public void gætBogstav(String identifier, String bogstav) {
        if (bogstav.length() != 1) {
            return;
        }
        System.out.println("Der g�ttes på bogstavet: " + bogstav);
        if (brugteBogstaver.contains(bogstav)) {
            return;
        }
        if (spilletErVundet || spilletErTabt) {
            return;
        }

        brugteBogstaver.add(bogstav);

        if (ordet.contains(bogstav)) {
            sidsteBogstavVarKorrekt = true;
            System.out.println("Bogstavet var korrekt: " + bogstav);
        } else {
            // Vi g�ttede på et bogstav der ikke var i ordet.
            sidsteBogstavVarKorrekt = false;
            System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
            antalForkerteBogstaver = antalForkerteBogstaver + 1;
            if (antalForkerteBogstaver > 6) {
                spilletErTabt = true;
            }
        }
        opdaterSynligtOrd(identifier);
    }

    @Override
    public void logStatus(String identifier) {
        System.out.println("---------- ");
        System.out.println("- ordet (skult) = " + ordet);
        System.out.println("- synligtOrd = " + synligtOrd);
        System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
        System.out.println("- brugeBogstaver = " + brugteBogstaver);
        if (spilletErTabt) {
            System.out.println("- SPILLET ER TABT");
        }
        if (spilletErVundet) {
            System.out.println("- SPILLET ER VUNDET");
        }
        System.out.println("---------- ");
    }

    public static String hentUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }

    @Override
    public void hentOrdFraDr(String identifier) throws IOException {
        String data = hentUrl("https://dr.dk");
        //System.out.println("data = " + data);

        data = data.substring(data.indexOf("<body")). // fjern headere
                replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
                replaceAll("&#198;", "�"). // erstat HTML-tegn
                replaceAll("&#230;", "�"). // erstat HTML-tegn
                replaceAll("&#216;", "ø"). // erstat HTML-tegn
                replaceAll("&#248;", "ø"). // erstat HTML-tegn
                replaceAll("&oslash;", "ø"). // erstat HTML-tegn
                replaceAll("&#229;", "å"). // erstat HTML-tegn
                replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
                replaceAll(" [a-zæøå] ", " "). // fjern 1-bogstavsord
                replaceAll(" [a-zæøå][a-zæøå] ", " "); // fjern 2-bogstavsord

        System.out.println("data = " + data);
        System.out.println("data = " + Arrays.asList(data.split("\\s+")));
        muligeOrd.clear();
        muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

        System.out.println("muligeOrd = " + muligeOrd);
        nulstil(identifier);
    }

    public void hentOrdFraDrTV(String identifier) throws MalformedURLException, IOException {
        URL url = new URL("https://www.dr.dk/mu-online/api/1.0/page/tv/front");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());

        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String output;
        System.out.println("Output from server... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);

        }

        conn.disconnect();

    }
    @Override
    public GameState updateGameState (String identifier , String letter)
    {
        GameState state = new GameState();
        gætBogstav(identifier, letter);
        state.SpilletErTabt = this.spilletErTabt;
        state.spillertErVundet = this.spilletErVundet;
        state.antalForkerteBogstaver = this.antalForkerteBogstaver;
        state.ordet = this.ordet;
        state.synligtOrd = this.synligtOrd;
        state.sidsteBogstavVarKorrekt = this.sidsteBogstavVarKorrekt;
        state.brugteBogstaver = this.brugteBogstaver;
        return state;
        
    }
    @Override
    public GameState getGameState (String identifier)
    {
        GameState state = new GameState();
        state.SpilletErTabt = this.spilletErTabt;
        state.spillertErVundet = this.spilletErVundet;
        state.antalForkerteBogstaver = this.antalForkerteBogstaver;
        state.ordet = this.ordet;
        state.synligtOrd = this.synligtOrd;
        state.sidsteBogstavVarKorrekt = this.sidsteBogstavVarKorrekt;
        state.brugteBogstaver = this.brugteBogstaver;
        return state;
    }

}

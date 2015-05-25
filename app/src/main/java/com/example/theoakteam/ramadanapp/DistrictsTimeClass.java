package com.example.theoakteam.ramadanapp;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hasan Abdullah on 24-May-15.
 */
public class DistrictsTimeClass {

    private Map<String,String> districtTimeMap = new HashMap<String,String>();
    private String[] centralSehriTime = new String[35];
    private String[] centralIftarTime = new String[35];



    public String grtDistrictTime(String districtKey){
        //fg;
        districtTimeMap.put("barguna", "2");
        districtTimeMap.put("barisal", "0");// Check it
        districtTimeMap.put("bhola", "-2");
        districtTimeMap.put("jhalokati", "1");
        districtTimeMap.put("patuakhali", "1");
        districtTimeMap.put("pirojpur", "2"); //Barisal End
        districtTimeMap.put("bandarban", "-8");
        districtTimeMap.put("brahmanbaria", "-3");
        districtTimeMap.put("chandpur", "-2");
        districtTimeMap.put("chittagong", "-6");
        districtTimeMap.put("comilla", "-4");
        districtTimeMap.put("cox's bazar", "-7");
        districtTimeMap.put("feni", "-5");
        districtTimeMap.put("khagrachhari", "-7");
        districtTimeMap.put("lakshmipur", "-2");
        districtTimeMap.put("noakhali", "-3");
        districtTimeMap.put("rangamati", "-8"); //Chittagong End
        districtTimeMap.put("dhaka", "0");
        districtTimeMap.put("faridpur", "3");
        districtTimeMap.put("gazipur", "0");
        districtTimeMap.put("gopalganj", "3");
        districtTimeMap.put("jamalpur", "2");
        districtTimeMap.put("kishoreganj", "-2");
        districtTimeMap.put("madaripur", "1");
        districtTimeMap.put("manikganj", "2");
        districtTimeMap.put("munshiganj", "-1");
        districtTimeMap.put("mymensingh", "-1");
        districtTimeMap.put("narayanganj", "-1");
        districtTimeMap.put("narsingdi", "-2");
        districtTimeMap.put("netrakona", "-2");
        districtTimeMap.put("rajbari", "2");
        districtTimeMap.put("shariatpur", "0");// Check it
        districtTimeMap.put("sherpur", "2");
        districtTimeMap.put("tangail", "2"); //Dhaka End
        districtTimeMap.put("bagerhat", "3");
        districtTimeMap.put("chuadanga", "7");
        districtTimeMap.put("jessore", "5");
        districtTimeMap.put("jhenaidah", "5");
        districtTimeMap.put("khulna", "4");
        districtTimeMap.put("kushtia", "5");
        districtTimeMap.put("magura", "4");
        districtTimeMap.put("meherpur", "8");
        districtTimeMap.put("narail", "4");
        districtTimeMap.put("satkhira", "6"); //Khulna End
        districtTimeMap.put("bogra", "5");
        districtTimeMap.put("chapainawabganj", "9");
        districtTimeMap.put("joypurhat", "6");
        districtTimeMap.put("pabna", "5");
        districtTimeMap.put("naogaon", "6");
        districtTimeMap.put("natore", "6");
        districtTimeMap.put("rajshahi", "7");
        districtTimeMap.put("sirajganj", "3"); //Rajshahi End
        districtTimeMap.put("dinajpur", "7");
        districtTimeMap.put("gaibandha", "4");
        districtTimeMap.put("kurigram", "3");
        districtTimeMap.put("lalmonirhat", "4");
        districtTimeMap.put("nilphamari", "7");
        districtTimeMap.put("panchagarh", "8");
        districtTimeMap.put("rangpur", "5");
        districtTimeMap.put("thakurgaon", "8"); //Rangpur End
        districtTimeMap.put("habiganj", "-5");
        districtTimeMap.put("moulvibazar ", "-6");
        districtTimeMap.put("sunamganj", "-4");
        districtTimeMap.put("sylhet", "-6"); //Sylhet End

        return  districtTimeMap.get(districtKey);

    }

    public void setCentralSehriTime(){

        centralSehriTime[1] = "6:49";
        centralSehriTime[2] = "6:49";
        centralSehriTime[3] = "6:50";
        centralSehriTime[4] = "6:50";
        centralSehriTime[5] = "6:50";
        centralSehriTime[6] = "6:51";
        centralSehriTime[7] = "6:51";
        centralSehriTime[8] = "6:52";
        centralSehriTime[9] = "6:52";
        centralSehriTime[10] = "6:52";
        centralSehriTime[11] = "6:52";
        centralSehriTime[12] = "6:53";
        centralSehriTime[13] = "6:53";
        centralSehriTime[14] = "6:54";
        centralSehriTime[15] = "6:54";
        centralSehriTime[16] = "6:54";
        centralSehriTime[17] = "6:55";
        centralSehriTime[18] = "6:55";
        centralSehriTime[19] = "6:54";
        centralSehriTime[20] = "6:54";
        centralSehriTime[21] = "6:54";
        centralSehriTime[22] = "6:54";
        centralSehriTime[23] = "6:53";
        centralSehriTime[24] = "6:53";
        centralSehriTime[25] = "6:53";
        centralSehriTime[26] = "6:53";
        centralSehriTime[27] = "6:52";
        centralSehriTime[28] = "6:52";
        centralSehriTime[29] = "6:52";
        centralSehriTime[30] = "6:51";

    }

    public void setCentralIftarTime(){
        centralIftarTime[1] = "3:37";
        centralIftarTime[2] = "3:37";
        centralIftarTime[3] = "3:37";
        centralIftarTime[4] = "3:38";
        centralIftarTime[5] = "3:38";
        centralIftarTime[6] = "3:38";
        centralIftarTime[7] = "3:39";
        centralIftarTime[8] = "3:39";
        centralIftarTime[9] = "3:40";
        centralIftarTime[10] = "3:40";
        centralIftarTime[11] = "3:41";
        centralIftarTime[12] = "3:41";
        centralIftarTime[13] = "3:42";
        centralIftarTime[14] = "3:42";
        centralIftarTime[15] = "3:42";
        centralIftarTime[16] = "3:43";
        centralIftarTime[17] = "3:43";
        centralIftarTime[18] = "3:44";
        centralIftarTime[19] = "3:44";
        centralIftarTime[20] = "3:45";
        centralIftarTime[21] = "3:45";
        centralIftarTime[22] = "3:46";
        centralIftarTime[23] = "3:46";
        centralIftarTime[24] = "3:47";
        centralIftarTime[25] = "3:48";
        centralIftarTime[26] = "3:48";
        centralIftarTime[27] = "3:49";
        centralIftarTime[28] = "3:49";
        centralIftarTime[29] = "3:52";
        centralIftarTime[30] = "3:51";
    }



}

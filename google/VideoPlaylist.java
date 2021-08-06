package com.google;

import java.util.ArrayList;

/** A class used to represent a Playlist */
class VideoPlaylist
{
    String playlistName;
    ArrayList<String> arrVideoID = new ArrayList<String>();


    void namePlaylist(String name)
    {
        playlistName = name;
    }


    void addVideoID(String ID)
    {
        arrVideoID.add(ID);
    }


    String playlistName()
    {
        return playlistName;
    }


    ArrayList<String> arrVideoID()
    {
        return arrVideoID;
    }
}

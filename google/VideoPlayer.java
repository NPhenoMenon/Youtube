package com.google;

import java.util.*;

public class VideoPlayer {

  ArrayList<VideoPlaylist> arrObj = new ArrayList<VideoPlaylist>();
  private final VideoLibrary videoLibrary;
  boolean isPlaying = false;
  boolean isPaused = false;
  String playingVideo;


  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos()
  {
    System.out.println("Here's a list of all available videos:");
    int size = videoLibrary.getVideos().size();
    String str[] = new String[size];

    for(int i = 0; i < size; i++)//Traverse through videoLibrary
    {
      String title = videoLibrary.getVideos().get(i).getTitle();
      String id = videoLibrary.getVideos().get(i).getVideoId();
      str[i] = title + " (" + id + ") [";
      List<String> tags = videoLibrary.getVideos().get(i).getTags();
      int tagSize = tags.size();

      for(int j = 0; j < tagSize; j++)//Traverse through tags list
      {
        if(j == 0)
        {
          str[i] = str[i] + tags.get(0);
        }
        else
        {
          str[i] = str[i] + " " + tags.get(j);
        }
      }

      str[i] = str[i] + "]";
    }

    String c;

    for(int a = 1; a < size; a++)//Insertion Sort
    {
      for(int b = 0; b <= a; b++)
      {
        if(str[a].compareTo(str[b]) <= 0)
        {
          c = str[a];
          str[a] = str[b];
          str[b] = c;
        }
      }
    }

    for(int i = 0; i < size; i++)//output
    {
      System.out.println(str[i]);
    }
  }

  public void playVideo(String videoId)
  {
    isPaused = false;
    boolean z = false;
    int size = videoLibrary.getVideos().size();

    for(int i = 0; i < size; i++)
    {
      if(videoId.trim().compareTo(videoLibrary.getVideos().get(i).getVideoId().trim()) == 0)
      {
        z = true;
      }
    }

    if(z == true)
    {
      if(isPlaying == true)
      {
        System.out.println("Stopping video: " + playingVideo);
      }

      String title = videoLibrary.getVideo(videoId).getTitle();
      System.out.println("Playing video: " + title);
      isPlaying = true;
      playingVideo = title;
    }
    else
    {
      System.out.println("Cannot play video: Video does not exist");
    }
  }

  public void stopVideo()
  {
    if(isPlaying == true)
    {
      System.out.println("Stopping video: " + playingVideo);
      isPlaying = false;
    }
    else
    {
      System.out.println("Cannot stop video: No video is currently playing");
    }
  }

  public void playRandomVideo()
  {
    isPaused = false;
    Random rand = new Random();
    int size = videoLibrary.getVideos().size();
    int randN = rand.nextInt(size);
    String randTitle = videoLibrary.getVideos().get(randN).getTitle();

    if(isPlaying == true)
    {
      System.out.println("Stopping video: " + playingVideo);
    }

    System.out.println("Playing video: " + randTitle);
    isPlaying = true;
    playingVideo = randTitle;
  }

  public void pauseVideo()
  {
    if(isPlaying == false)
    {
      System.out.println("Cannot pause video: No video is currently playing");
    }
    else if(isPaused == false)
    {
      System.out.println("Pausing video: " + playingVideo);
      isPaused = true;
    }
    else if(isPaused == true)
    {
      System.out.println("Video already paused: " + playingVideo);
    }
  }

  public void continueVideo()
  {
    if(isPlaying == false)
    {
      System.out.println("Cannot continue video: No video is currently playing");
    }
    else if(isPaused == true)
    {
      System.out.println("Continuing video: " + playingVideo);
      isPaused = false;
    }
    else if(isPaused == false)
    {
      System.out.println("Cannot continue video: Video is not paused");
    }
  }

  public void showPlaying()
  {
    if(isPlaying == false)
    {
      System.out.println("No video is currently playing");
    }
    else
    {
      int size = videoLibrary.getVideos().size();
      int index = 0;

      for(int i = 0; i < size; i++)
      {
        String strCompare = videoLibrary.getVideos().get(i).getTitle();

        if(playingVideo.compareTo(strCompare) == 0)
        {
          index = i;
        }
      }

      String id = videoLibrary.getVideos().get(index).getVideoId();
      System.out.print("Currently playing: " + playingVideo + " (" + id + ") [");
      List<String> tags = videoLibrary.getVideos().get(index).getTags();
      int tagSize = tags.size();

      for(int j = 0; j < tagSize; j++)
      {
        if(j == 0)
        {
          System.out.print(tags.get(0));
        }
        else
        {
          System.out.print(" " + tags.get(j));
        }
      }

      System.out.print("]");

      if(isPaused == true)
      {
        System.out.print(" - PAUSED");
      }

      System.out.println();
    }
  }

  public void createPlaylist(String playlistName)
  {
    boolean whitespaceCheck = true;
    boolean z = true;
    int obSize = arrObj.size();
    int strLength = playlistName.length();

    for(int i = 0; i < strLength; i++)//Traverse through the object array from VideoPlaylist class
    {
      if(Character.isWhitespace(playlistName.charAt(i)) == true)
      {
        whitespaceCheck = false;
      }
    }

    for(int i = 0; i < obSize; i++)
    {
      if(playlistName.equalsIgnoreCase(arrObj.get(i).playlistName) == true)
      {
        z = false;
      }
    }

    if(whitespaceCheck == false)
    {
      System.out.println("Playlist name should not contain any whitespaces.");
    }
    else if(z == false)
    {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
    }
    else
    {
      VideoPlaylist ob = new VideoPlaylist();
      ob.namePlaylist(playlistName);
      arrObj.add(ob);
      System.out.println("Successfully created new playlist: " + playlistName);
    }
  }

  public void addVideoToPlaylist(String playlistName, String videoId)
  {
    boolean existPlaylistName = false;
    int obSize = arrObj.size();
    int index = 0;

    for(int i = 0; i < obSize; i++)
    {
      if(playlistName.equalsIgnoreCase(arrObj.get(i).playlistName()) == true)
      {
        existPlaylistName = true;
        index = i;
      }
    }

    if(existPlaylistName == false)
    {
      System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
    }
    else
    {
      boolean z = true;
      int IDSize = arrObj.get(index).arrVideoID.size();

      for(int i = 0; i < IDSize; i++)//Traverse through the id within a single playlist
      {
        if(videoId.compareTo(arrObj.get(index).arrVideoID().get(i)) == 0)
        {
          z = false;
        }
      }

      boolean existID = false;
      int librarySize = videoLibrary.getVideos().size();

      for(int i = 0; i < librarySize; i++)
      {
        if(videoId.compareTo(videoLibrary.getVideos().get(i).getVideoId()) == 0)
        {
          existID = true;
        }
      }

      if (z == false) {
        System.out.println("Cannot add video to " + playlistName + ": Video already added");
      } else if (existID == false) {
        System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
      } else {
        arrObj.get(index).addVideoID(videoId);
        String title = videoLibrary.getVideo(videoId).getTitle();
        System.out.println("Added video to " + playlistName + ": " + title);
      }
    }
  }

  public void showAllPlaylists()
  {
    int obSize = arrObj.size();
    String output[] = new String[obSize];

    if(obSize > 0)
    {
      System.out.println("Showing all playlists:");

      for(int i = 0; i < obSize; i++)
      {
        output[i] = arrObj.get(i).playlistName();
      }

      String c;

      for(int a = 1; a < obSize; a++)//Insertion sort
      {
        for(int b = 0; b <= a; b++)
        {
          if(output[a].compareTo(output[b]) <= 0)
          {
            c = output[a];
            output[a] = output[b];
            output[b] = c;
          }
        }
      }

      for(int i = 0; i < obSize; i++)
      {
        System.out.println("\t" + output[i]);
      }
    }
    else
    {
      System.out.println("No playlists exist yet");
    }
  }

  public void showPlaylist(String playlistName)
  {
    boolean existPlaylistName = false;
    int obSize = arrObj.size();
    int nVideos = 0;

    for(int i = 0; i < obSize; i++)
    {
      if(playlistName.equalsIgnoreCase(arrObj.get(i).playlistName) == true)
      {
        existPlaylistName = true;
        nVideos = arrObj.get(i).arrVideoID().size();
      }
    }

    if(existPlaylistName == false)
    {
      System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
    }
    else if(nVideos == 0)
    {
      System.out.println("Showing playlist: " + playlistName);
      System.out.println("\tNo videos here yet");
    }
    else
    {
      System.out.println("Showing playlist: " + playlistName);

      for(int i = 0; i < obSize; i++)
      {
        if(playlistName.equalsIgnoreCase(arrObj.get(i).playlistName) == true)
        {
          for(int j = 0; j < nVideos; j++)
          {
            String id = arrObj.get(i).arrVideoID().get(j);
            String title = videoLibrary.getVideo(id).getTitle();
            List<String> tags = videoLibrary.getVideo(id).getTags();
            String output = "\t" + title + " (" + id + ") [";
            int tagSize = tags.size();

            for(int k = 0; k < tagSize; k++)
            {
              if(k == 0)
              {
                output = output + tags.get(0);
              }
              else
              {
                output = output + " " + tags.get(k);
              }
            }

            output = output + "]";
            System.out.println(output);
          }
        }
      }
    }
  }

  public void removeFromPlaylist(String playlistName, String videoId)
  {
    boolean existPlaylist = false;
    boolean existVideoPlaylist = false;
    int obSize = arrObj.size();
    int indexPlaylist = 0;
    int indexID = 0;

    for(int i = 0; i < obSize; i++)
    {
      if(playlistName.equalsIgnoreCase(arrObj.get(i).playlistName) == true)
      {
        existPlaylist = true;
        int nVideos = arrObj.get(i).arrVideoID().size();
        indexPlaylist = i;

        for(int j = 0; j < nVideos; j++)
        {
          if(videoId.compareTo(arrObj.get(i).arrVideoID().get(j)) == 0)
          {
            existVideoPlaylist = true;
            indexID = j;
          }
        }
      }
    }

    boolean existVideo = false;
    int size = videoLibrary.getVideos().size();

    for(int i = 0; i < size; i++)
    {
      if(videoId.compareTo(videoLibrary.getVideos().get(i).getVideoId()) == 0)
      {
        existVideo = true;
      }
    }

    if(existPlaylist == false)
    {
      System.out.println("Cannot remove video from " + playlistName + ": Playlist does not exist");
    }
    else if(existVideo == false)
    {
      System.out.println("Cannot remove video from " + playlistName + ": Video does not exist");
    }
    else if(existVideoPlaylist == false)
    {
      System.out.println("Cannot remove video from " + playlistName + ": Video is not in playlist");
    }
    else
    {
      arrObj.get(indexPlaylist).arrVideoID().remove(indexID);
      String title = videoLibrary.getVideo(videoId).getTitle();
      System.out.println("Removed video from " + playlistName + ": " + title);
    }
  }

  public void clearPlaylist(String playlistName)
  {
    boolean existPlaylist = false;
    int obSize = arrObj.size();

    for(int i = 0; i < obSize; i++)
    {
      if(playlistName.equalsIgnoreCase(arrObj.get(i).playlistName) == true)
      {
        existPlaylist = true;
        int nVideos = arrObj.get(i).arrVideoID().size();

        for(int j = 0; j < nVideos; j++)
        {
          arrObj.get(i).arrVideoID().remove(j);
        }
      }
    }

    if(existPlaylist == false)
    {
      System.out.println("Cannot clear playlist " + playlistName + ": Playlist does not exist");
    }
    else
    {
      System.out.println("Successfully removed all videos from " + playlistName);
    }
  }

  public void deletePlaylist(String playlistName)
  {
    boolean existPlaylist = false;
    int obSize = arrObj.size();

    for(int i = 0; i < obSize; i++)
    {
      if(playlistName.equalsIgnoreCase(arrObj.get(i).playlistName) == true)
      {
        existPlaylist = true;
        arrObj.remove(i);
      }
    }

    if(existPlaylist == false)
    {
      System.out.println("Cannot delete playlist " + playlistName + ": Playlist does not exist");
    }
    else
    {
      System.out.println("Deleted playlist: " + playlistName);
    }
  }

  public void searchVideos(String searchTerm)
  {
    String lSearchTerm = searchTerm.toLowerCase();
    boolean z = false;
    int size = videoLibrary.getVideos().size();
    int nSearchTerms = 0;

    for(int i = 0; i < size; i++)
    {
      String lTitle = videoLibrary.getVideos().get(i).getTitle().toLowerCase();

      if(lTitle.contains(lSearchTerm) == true)
      {
        z = true;
        nSearchTerms++;
      }
    }

    if(z == false)
    {
      System.out.println("No search results for " + searchTerm);
    }
    else
    {
      System.out.println("Here are the results for " + searchTerm + ":");
      String output[] = new String[nSearchTerms];
      int j = 0;

      for(int i = 0; i < size; i++)
      {
        String lTitle = videoLibrary.getVideos().get(i).getTitle().toLowerCase();

        if(lTitle.contains(lSearchTerm) == true)
        {
          String title = videoLibrary.getVideos().get(i).getTitle();
          String id = videoLibrary.getVideos().get(i).getVideoId();
          output[j] = title + " (" + id + ") [";
          List<String> tags = videoLibrary.getVideos().get(i).getTags();
          int tagSize = tags.size();

          for(int k = 0; k < tagSize; k++)
          {
            if(k == 0)
            {
              output[j] = output[j] + tags.get(0);
            }
            else
            {
              output[j] = output[j] + " " + tags.get(k);
            }
          }

          output[j] = output[j] + "]";
          j++;
        }
      }

      String c;

      for(int a = 1; a < nSearchTerms; a++)//Insertion Sort
      {
        for(int b = 0; b <= a; b++)
        {
          if(output[a].compareTo(output[b]) <= 0)
          {
            c = output[a];
            output[a] = output[b];
            output[b] = c;
          }
        }
      }

      for(int i = 0; i < nSearchTerms; i++)
      {
        System.out.println("\t" + (i + 1) + ") " + output[i]);
      }

      System.out.println("Would you like to play any of the above? If yes, specify the number of the video.");
      System.out.println("If your answer is not a valid number, we will assume it's a no.");
      Scanner sc = new Scanner(System.in);
      String input = sc.nextLine();

      if(input.chars().allMatch(Character :: isDigit) == true)
      {
        int n = Integer.parseInt(input);

        if(n >= 1 && n <= nSearchTerms)
        {
          String chosen = output[n - 1];
          int index1 = chosen.indexOf("(");
          int index2 = chosen.indexOf(")");
          String id = chosen.substring((index1 + 1), index2);
          this.playVideo(id);
        }
      }
    }
  }

  public void searchVideosWithTag(String videoTag)
  {
    boolean z = false;
    int size = videoLibrary.getVideos().size();
    int nTags = 0;

    for(int i = 0; i < size; i++)
    {
      List<String> tags = videoLibrary.getVideos().get(i).getTags();

      for(int j = 0; j < tags.size(); j++)
      {
        if(videoTag.equalsIgnoreCase(tags.get(j)) == true)
        {
          z = true;
          nTags++;
          break;
        }
      }
    }

    if(z == false)
    {
      System.out.println("No search results for " + videoTag);
    }
    else
    {
      System.out.println("Here are the results for " + videoTag + ":");
      String output[] = new String[nTags];
      int k = 0;

      for(int i = 0; i < size; i++)
      {
        List<String> tags = videoLibrary.getVideos().get(i).getTags();

        for(int j = 0; j < tags.size(); j++)
        {
          if(videoTag.equalsIgnoreCase(tags.get(j)) == true)
          {
            String title = videoLibrary.getVideos().get(i).getTitle();
            String id = videoLibrary.getVideos().get(i).getVideoId();
            output[k] = title + " (" + id + ") [";
            int tagSize = tags.size();

            for(int x = 0; x < tagSize; x++)
            {
              if(x == 0)
              {
                output[k] = output[k] + tags.get(0);
              }
              else
              {
                output[k] = output[k] + " " + tags.get(x);
              }
            }

            output[k] = output[k] + "]";
            k++;
            break;
          }
        }
      }

      String c;

      for(int a = 1; a < nTags; a++)//Insetion Sort
      {
        for(int b = 0; b <= a; b++)
        {
          if(output[a].compareTo(output[b]) <= 0)
          {
            c = output[a];
            output[a] = output[b];
            output[b] = c;
          }
        }
      }

      for(int i = 0; i < nTags; i++)
      {
        System.out.println("\t" + (i + 1) + ") " + output[i]);
      }

      System.out.println("Would you like to play any of the above? If yes, specify the number of the video.");
      System.out.println("If your answer is not a valid number, we will assume it's a no.");
      Scanner sc = new Scanner(System.in);
      String input = sc.nextLine();

      if(input.chars().allMatch(Character :: isDigit) == true)
      {
        int n = Integer.parseInt(input);

        if(n >= 1 && n <= nTags)
        {
          String chosen = output[n - 1];
          int index1 = chosen.indexOf("(");
          int index2 = chosen.indexOf(")");
          String id = chosen.substring((index1 + 1), index2);
          this.playVideo(id);
        }
      }
    }
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}
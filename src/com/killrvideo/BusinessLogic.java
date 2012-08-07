package com.killrvideo;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.serializers.UUIDSerializer;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.SliceQuery;

public class BusinessLogic {

	private static StringSerializer stringSerializer = StringSerializer.get();
	private static UUIDSerializer uuidSerializer = UUIDSerializer.get();

	public void setUser(User user, Keyspace keyspace) {

		Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);

		try {

			mutator.addInsertion(user.getUsername(), "users",
					HFactory.createStringColumn("firstname", user.getFirstname()));
			mutator.addInsertion(user.getUsername(), "users",
					HFactory.createStringColumn("lastname", user.getLastname()));
			mutator.addInsertion(user.getUsername(), "users",
					HFactory.createStringColumn("password", user.getPassword()));

			mutator.execute();
		} catch (HectorException he) {
			throw he;
		}
	}

	public User getUser(String username) {

		// TODO Implement this method

		return null;
	}

	public void setVideo(Video video, Keyspace keyspace) {
		
		Mutator<UUID> mutator = HFactory.createMutator(keyspace, UUIDSerializer.get());

		try {
			mutator.addInsertion(video.getVideoId(), "videos", HFactory.createStringColumn("videoname", video.getVideoName()));
			mutator.addInsertion(video.getVideoId(), "videos", HFactory.createStringColumn("username", video.getUsername()));
			mutator.addInsertion(video.getVideoId(), "videos", HFactory.createStringColumn("description", video.getDescription()));
			mutator.addInsertion(video.getVideoId(), "videos", HFactory.createStringColumn("tags", video.getDelimitedTags()));

			mutator.execute();
		} catch (HectorException he) {
			he.printStackTrace();
		}
	}

	public Video getVideoByUUID(UUID videoId, Keyspace keyspace) {

		Video video = new Video();

		// Create a slice query. We'll be getting specific column names
		SliceQuery<UUID, String, String> sliceQuery = HFactory.createSliceQuery(keyspace, uuidSerializer,
				stringSerializer, stringSerializer);
		sliceQuery.setColumnFamily("videos");
		sliceQuery.setKey(videoId);

		sliceQuery.setColumnNames("videoname", "username", "description", "tags");

		// Execute the query and get the list of columns
		ColumnSlice<String, String> result = sliceQuery.execute().get();

		// Get each column by name and add them to our video object
		video.setVideoName(result.getColumnByName("videoname").getValue());
		video.setUsername(result.getColumnByName("username").getValue());
		video.setDescription(result.getColumnByName("description").getValue());
		video.setTags(result.getColumnByName("tags").getValue().split(","));

		return video;
	}

	public void setVideoWithTagIndex(Video video, Keyspace keyspace) {

		Mutator<UUID> UUIDmutator = HFactory.createMutator(keyspace, UUIDSerializer.get());
		Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);

		try {

			UUIDmutator.addInsertion(video.getVideoId(), "videos", HFactory.createStringColumn("videoname", video.getVideoName()));
			UUIDmutator.addInsertion(video.getVideoId(), "videos", HFactory.createStringColumn("username", video.getUsername()));
			UUIDmutator.addInsertion(video.getVideoId(), "videos", HFactory.createStringColumn("description", video.getDescription()));
			UUIDmutator.addInsertion(video.getVideoId(), "videos", HFactory.createStringColumn("tags", video.getDelimitedTags()));

			for(String tag: video.getTags()){
				mutator.addInsertion(tag, "tag_index", HFactory.createStringColumn(video.getVideoId().toString(), ""));
			}

			UUIDmutator.execute();
			mutator.execute();

		} catch (HectorException he) {
			he.printStackTrace();
		}

	}

	public void setVideoWithUserIndex(Video video, Keyspace keyspace) {
		// TODO Implement this method


	}

	public void setComment(Video video, String comment, Timestamp timestamp, Keyspace keyspace) {

		Mutator<UUID> mutator = HFactory.createMutator(keyspace, UUIDSerializer.get());

		try {
			String columnName = video.getUsername() + ":" + timestamp;
			mutator.addInsertion(video.getVideoId(), "comments", HFactory.createStringColumn(columnName, comment));

			mutator.execute();
		} catch (HectorException he) {
			he.printStackTrace();
		}
	}

	public ArrayList<String> getComments(UUID videoId) {
		// TODO Implement
		
		return null;
	}

	public void setRating(UUID videoId, long ratingNumber, Keyspace keyspace) {
		Mutator<UUID> mutator = HFactory.createMutator(keyspace, UUIDSerializer.get());

		try {

			mutator.addCounter(videoId, "video_rating", HFactory.createCounterColumn("rating_count", 1));
			mutator.addCounter(videoId, "video_rating", HFactory.createCounterColumn("rating_total", ratingNumber));

			mutator.execute();
		} catch (HectorException he) {
			he.printStackTrace();
		}
	}

	public float getRating(UUID videoId) {
		// TODO Implement
		
		
		return 0;
	}

	public void setVideoStartEvent(UUID videoId, String username, Timestamp timestamp, Keyspace keyspace) {

		Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);

		try {

			mutator.addInsertion(username + ":" + videoId, "video_event",
					HFactory.createStringColumn("start:" + timestamp, ""));

			mutator.execute();
		} catch (HectorException he) {
			he.printStackTrace();
		}

	}

	public void setVideoStopEvent(UUID videoId, String username, Timestamp stopEvent, Timestamp videoTimestamp,
			Keyspace keyspace) {
		Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);

		try {

			mutator.addInsertion(username + ":" + videoId, "video_event",
					HFactory.createStringColumn("stop:" + stopEvent, videoTimestamp.toString()));

			mutator.execute();
		} catch (HectorException he) {
			he.printStackTrace();
		}
	}

	public Timestamp getVideoLastStopEvent(UUID videoId, String username) {
		// TODO Implement
		
		return null;
	}
}

package com.killrvideo.test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import com.killrvideo.Video;

public class VideoTest {


	@Test
	public void testGetDelimitedTags() {
		Video video = new Video(UUID.randomUUID(), "Funny Cat Video", "pmcfadin", "Comment", new String[]{"1","2","3"});
		
		assertEquals("1,2,3", video.getDelimitedTags());
	}

}

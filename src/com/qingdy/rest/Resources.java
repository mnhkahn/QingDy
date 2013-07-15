package com.qingdy.rest;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qingdy.domain.QdHistory;

@Path("/metadata")
@Produces(MediaType.APPLICATION_JSON)
public class Resources {

	@Path("/history")
	@GET
	public List<String> getHistory() {
		List<String> list = new LinkedList<String>();
		list.add("hello");
		return list;
	}
	
	@Path("/history")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public QdHistory addHistory(QdHistory history) {
//		System.out.println(history.getUid());
		return history;
	}
}

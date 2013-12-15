package com.qingdy.dao.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qingdy.dao.TimelineDao;
import com.qingdy.model.Timeline;
import com.qingdy.model.UserDetail;

@Service("timelineDao")
public class TimelineDaoHibernate extends BaseDaoHibernate implements TimelineDao {

	@Override
	public List<Timeline> getTimelines(int size, int page) {
		List<Object[]> lists = getHibernateTemplate().findByNamedQuery("queryAnswerTimeline");
		lists.addAll(getHibernateTemplate().findByNamedQuery("queryBlogTimeline"));
		lists.addAll(getHibernateTemplate().findByNamedQuery("queryEvaluateTimeline"));
		lists.addAll(getHibernateTemplate().findByNamedQuery("queryLoanTimeline"));
		lists.addAll(getHibernateTemplate().findByNamedQuery("queryMallTimeline"));
		lists.addAll(getHibernateTemplate().findByNamedQuery("queryProductTimeline"));
		lists.addAll(getHibernateTemplate().findByNamedQuery("queryQuestionTimeline"));
		lists.addAll(getHibernateTemplate().findByNamedQuery("queryTransactionTimeline"));
		
		Collections.sort(lists, new Comparator<Object[]>() {
		    public int compare(Object[] o1, Object[] o2) {
		    	Date d1 = (Date)o1[2];
		    	Date d2 = (Date)o2[2];
		        return -1 * d1.compareTo(d2);
		    }
		});
		
		List<Timeline> timelines = new ArrayList<>();
		int n = (lists.size() > size) ? size : lists.size();
		for (int i = 0; i < n; i++) {
			Timeline timeline = new Timeline();
			timeline.setId((Long)lists.get(i)[0]);
			UserDetail user = ((UserDetail)(lists.get(i)[1]));
			timeline.setName(user.getLastname() + user.getFirstname());
			timeline.setTime((Date)lists.get(i)[2]);
			timeline.setType((Integer)lists.get(i)[3]);
			
			timelines.add(timeline);
		}
		
		return timelines;
	}

	@Override
	public List<Timeline> geTimelinesByUser(String username, int size, int page) {

		List<Object[]> lists = getSession().getNamedQuery("queryAnswerTimelineByUser").setString(0, username).list();
		lists.addAll(getSession().getNamedQuery("queryBlogTimelineByUser").setString(0, username).list());
		lists.addAll(getSession().getNamedQuery("queryEvaluateTimelineByUser").setString(0, username).list());
		lists.addAll(getSession().getNamedQuery("queryLoanTimelineByUser").setString(0, username).list());
		lists.addAll(getSession().getNamedQuery("queryMallTimelineByUser").setString(0, username).list());
		lists.addAll(getSession().getNamedQuery("queryProductTimelineByUser").setString(0, username).list());
		lists.addAll(getSession().getNamedQuery("queryQuestionTimelineByUser").setString(0, username).list());
		lists.addAll(getSession().getNamedQuery("queryTransactionTimelineByUser").setString(0, username).list());
		if (size == 0) {
			size = lists.size();
		}

		Collections.sort(lists, new Comparator<Object[]>() {
		    public int compare(Object[] o1, Object[] o2) {
		    	Date d1 = (Date)o1[2];
		    	Date d2 = (Date)o2[2];
		        return -1 * d1.compareTo(d2);
		    }
		});
		
		List<Timeline> timelines = new ArrayList<>();
		int n = (lists.size() > size) ? size : lists.size();
		for (int i = 0; i < n; i++) {
			Timeline timeline = new Timeline();
			timeline.setId((Long)lists.get(i)[0]);
			UserDetail user = ((UserDetail)(lists.get(i)[1]));
			timeline.setName(user.getLastname() + user.getFirstname());
			timeline.setTime((Date)lists.get(i)[2]);
			timeline.setType((Integer)lists.get(i)[3]);
			
			timelines.add(timeline);
		}
		
		return timelines;
	}

}

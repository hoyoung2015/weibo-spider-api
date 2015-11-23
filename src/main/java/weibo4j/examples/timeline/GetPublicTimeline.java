package weibo4j.examples.timeline;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;


public class GetPublicTimeline {

	public static void main(String[] args) {
//		String access_token = args[0];
		String access_token = "2.00lpvwVCeiqcYDfd8882c1392WGmpC";
		Timeline tm = new Timeline(access_token);
		try {
			StatusWapper status = tm.getPublicTimeline(200,0);
			Log.logInfo(status.toString());
			System.out.println(">>>>>>>>>>>>>>>>"+status.getStatuses().size());
			System.out.println(">>>>>>>>>>>>>>>>"+status.getTotalNumber());
			System.out.println(">>>>>>>>>>>>>>>>"+status.getHasvisible());
			System.out.println(">>>>>>>>>>>>>>>>"+status.getNextCursor());
			System.out.println(">>>>>>>>>>>>>>>>"+status.getPreviousCursor());
			for (Status doc:status.getStatuses()){
				System.out.println(doc.toString());
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}

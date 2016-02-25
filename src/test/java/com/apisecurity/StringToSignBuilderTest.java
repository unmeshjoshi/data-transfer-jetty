package com.apisecurity;

import com.apisecurity.auth.StringToSignBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class StringToSignBuilderTest {

  //api.ta.com/silo/siloid/records?
  //api.ta.com/silo/siloid/bucket/bucketid/records?
  @Test
  public void shouldBuildStringToSignFromHttpRequestWithParametersSortedAlphabetically() {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    Map<String, String[]> queryMap = new HashMap<String, String[]>();
    queryMap.put("start-date", new String[]{"2012-12-12"});
    queryMap.put("end-date", new String[]{"2013-01-01"});
    queryMap.put("silo", new String[]{"UKHotels"});
    queryMap.put("bucket", new String[]{"Bucket2"});
    queryMap.put("group_by", new String[]{"silo,bucket,location_id"});

    when(request.getParameterMap()).thenReturn(queryMap);
    when(request.getHeader("Date")).thenReturn("Tue, 01 Jan 2013 19:36:42 +0000");
    String stringToSign = new StringToSignBuilder(request).build();
    assertEquals("Tue, 01 Jan 2013 19:36:42 +0000 bucket=Bucket2&end-date=2013-01-01&group_by=silo,bucket,location_id&silo=UKHotels&start-date=2012-12-12", stringToSign);

  }

  @Test
  public void shouldBuildStringToSignFromHttpRequestWithNoParameters() {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    Map<String, String[]> queryMap = new HashMap<String, String[]>();
    when(request.getParameterMap()).thenReturn(queryMap);
    when(request.getHeader("Date")).thenReturn("Tue, 01 Jan 2013 19:36:42 +0000");
    String stringToSign = new StringToSignBuilder(request).build();
    assertEquals("Tue, 01 Jan 2013 19:36:42 +0000", stringToSign);

  }


}

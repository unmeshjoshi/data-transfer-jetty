package com.apisecurity.auth;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class StringToSignBuilder {
  private HttpServletRequest request;

  public StringToSignBuilder(HttpServletRequest request) {
    this.request = request;
  }

  public String build() {
    String timeStamp = request.getHeader("Date");
    StringBuilder stringToSign = new StringBuilder();
    stringToSign.append(timeStamp);

    Map<String, String[]> parameters = request.getParameterMap();
    if (parameters.size() > 0) {
      stringToSign.append(" ");
      Set<String> sortedParameters = sortParameters(parameters);
      for (String sortedParameter : sortedParameters) {
        stringToSign.append(sortedParameter)
          .append("=")
          .append(parameters.get(sortedParameter)[0]);
        stringToSign.append("&");
      }

      return stringToSign.substring(0, stringToSign.lastIndexOf("&"));
    }
    return stringToSign.toString();
  }

  private Set<String> sortParameters(Map<String, String[]> parameters) {
    Set<String> sortedParameters = new TreeSet<String>();
    for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
      sortedParameters.add(entry.getKey());
    }
    return sortedParameters;
  }
}

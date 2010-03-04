package jp.co.softem.ikko.core;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("sample")
public class Sample {

	public String hello(Map<String, String[]> map) {
		String[] obj = map.get("loginId");
		return (obj == null) ? "not found" : obj[0];
	}
}
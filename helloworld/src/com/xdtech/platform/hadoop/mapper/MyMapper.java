package com.xdtech.platform.hadoop.mapper;

import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MyMapper extends MapReduceBase implements Mapper {

	public void map(WritableComparable key, Writable values,
			OutputCollector output, Reporter reporter) throws IOException {
	}

	@Override
	public void map(Object arg0, Object arg1, OutputCollector arg2,
			Reporter arg3) throws IOException {
		// TODO Auto-generated method stub
		
	}

}

/*=========================
   GradeDTO.java
=========================*/

package com.test.mybatis;

public class GradeDTO
{
	private String sid, name, sub1, sub2, sub3, tot, avg, ch;

	// getter / setter 구성
	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSub1()
	{
		return sub1;
	}

	public void setSub1(String sub1)
	{
		this.sub1 = sub1;
	}

	public String getSub2()
	{
		return sub2;
	}

	public void setSub2(String sub2)
	{
		this.sub2 = sub2;
	}

	public String getSub3()
	{
		return sub3;
	}

	public void setSub3(String sub3)
	{
		this.sub3 = sub3;
	}

	public String getTot()
	{
		return tot;
	}

	public void setTot(String tot)
	{
		this.tot = tot;
	}

	public String getAvg()
	{
		return avg;
	}

	public void setAvg(String avg)
	{
		this.avg = avg;
	}

	public String getCh()
	{
		return ch;
	}

	public void setCh(String ch)
	{
		this.ch = ch;
	}
	
	
}

package com.azoomee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Department{

	@Id
	@Column(name = "departmentID", nullable = false, updatable = false)
	@NotNull
	@Size(max = 64)
	private String departmentID;

	@Column(name = "name", nullable = false)
 	@NotNull
 	@Size(max = 64)
	private String name;

	public Department(){
	}

	public Department(final String departmentID, final String name){
		this.departmentID = departmentID;
		this.name = name;
	}

	public String getDepartmentID()
	{
		return departmentID;
	}

	public void setDepartmentID(final String departmentID)
	{
		this.departmentID = departmentID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final Department that = (Department) o;

		if (departmentID != null ? !departmentID.equals(that.departmentID) : that.departmentID != null)
		{
			return false;
		}
		return !(name != null ? !name.equals(that.name) : that.name != null);

	}

	@Override
	public int hashCode()
	{
		int result = departmentID != null ? departmentID.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}

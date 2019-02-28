#people

```$xslt
[
  '{{repeat(100)}}',
  {
    firstName: '{{firstName ()}}',
    lastName: '{{surname ()}}',
    contact:{
      phone:'{{phone()}}',      
      email:'{{email()}}'
    },
    address:{
		id:'{{integer([1],[10])}}'
	}
  }
]
```

#companies

```$xslt

[
  '{{repeat(100)}}',
  {
    name : '{{company()}}',
    owner : '{{random("user","george","mihai")}}',
    registryNo: '{{integer(1000,9999)}}'
  }
]

```

#addresses

```$xslt
[
  '{{repeat(100)}}',
  {
    streetName: '{{street ()}}'
  }
]
```
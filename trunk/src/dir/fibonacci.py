a=0
b=1
count=0
max_count=42
while count<max_count:
      count=count+1
      old_a=a
      old_b=b
      a=old_b
      b=old_a+old_b
      print old_a,#la virgola prosegue la stampa sulla stessa linea
print

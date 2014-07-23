#!/bin/sh
#Colonel Fitzwilliam or Fitzwilliam to Prateek Chandan
#Lady Catherine to Naveen Sagar
#Mr. Darcy or Darcy to Aditya Kumar Akash
sed 's/``/"/g' ./data/pnp_austen.txt > ./data/pnp_austen_cs296.txt
sed -i "s/''/\"/g" ./data/pnp_austen_cs296.txt 
sed -i 's/Colonel Fitzwilliam/Prateek Chandan/g;s/Lady Catherine/Naveen Sagar/g;s/Mr. Darcy/Aditya Kumar Akash/g;s/Darcy/Aditya Kumar Akash/g;s/Fitzwilliam/Prateek Chandan/g' ./data/pnp_austen_cs296.txt

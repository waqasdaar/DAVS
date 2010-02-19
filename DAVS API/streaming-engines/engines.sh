#~/bin/bash

# Distribution Agnostic Video Server
# List all available streaming engines
# on the DAVS server.
# Created by Waqas Daar (daar@kth.se)
# Date : 28 Februray 2009

declare -a AvailableEngines
declare -i index=1
declare -i TotalEngines
declare -i i=2
space=" "

EnginesDirectories=`find /home/waqasdaar/DAVS/bin -maxdepth 1 -type d -print`


for Engines in $(echo $EnginesDirectories)
do
   AvailableEngines[$index]="$Engines"
  ((index++))
done

       if [ ${#AvailableEngines[*]} -eq 1 ]
       then
                echo "Error: No streaming engine available in DAVS server."
                exit 1;
       else
                TotalEngines=${#AvailableEngines[*]}
                DAVS_Engines=""

                while [ $i -le $TotalEngines ]
                do
                         engine=`basename ${AvailableEngines[$i]}`
                         DAVS_Engines=$engine$space$DAVS_Engines
                         i=$[$i+1]
                done
                echo $DAVS_Engines
                exit 0;
        fi


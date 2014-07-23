#!/bin/bash
set -o history
history -r ~/.bash_history
echo "Top 10 commands used by $(whoami)"
echo "Frequency Command"
history | awk '{print $2}' | sort | uniq -c | sort -r -g | head

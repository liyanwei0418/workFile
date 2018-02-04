
echo export TMOUT=1000000 >> /root/.profile
cat /root/.profile
source .profile
cp /etc/ssh/ssh_config /etc/ssh/ssh_config_bak
echo ClientAliveInterval=60 >> /etc/ssh/ssh_config
service ssh restart
cat /etc/ssh/ssh_config
service ssh restart
exit

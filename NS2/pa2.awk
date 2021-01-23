BEGIN{
    c=0;
}
{
    if($1=="d"){
        c++;
    }
}
END{
    printf("Number of %s  packets dropped = %d",$5,c);
}
